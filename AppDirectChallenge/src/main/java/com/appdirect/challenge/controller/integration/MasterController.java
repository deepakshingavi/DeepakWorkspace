package com.appdirect.challenge.controller.integration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.client.action.ActionResult;
import com.appdirect.challenge.client.action.GetSubscriptionCancelEventAction;
import com.appdirect.challenge.client.action.GetSubscriptionOrderEventAction;
import com.appdirect.challenge.client.action.GetUserAssignedEventAction;
import com.appdirect.challenge.client.action.GetUserUnassignedEventAction;
import com.appdirect.challenge.domain.appdirect.User;
import com.appdirect.challenge.domain.appdirect.event.EventResult;
import com.appdirect.challenge.domain.appdirect.event.access.UserAssignedEvent;
import com.appdirect.challenge.domain.appdirect.event.access.UserUnassignedEvent;
import com.appdirect.challenge.domain.appdirect.event.subscription.SubscriptionCancelEvent;
import com.appdirect.challenge.domain.appdirect.event.subscription.SubscriptionOrderEvent;
import com.appdirect.challenge.domain.myapp.Profile;
import com.appdirect.challenge.logging.AutowiredLogger;
import com.appdirect.challenge.service.ProfileService;
import com.appdirect.challenge.service.appdirect.UserService;

/**
 * http://info.appdirect.com/developers/docs/api_integration/subscription_management
 */
@RestController
@RequestMapping("/appdirect")
public class MasterController {

    @AutowiredLogger
    Logger log;

    @Autowired
    AppDirectClient appDirectClient;

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/subscription/create")
    public EventResult createSubscription(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        GetSubscriptionOrderEventAction action = new GetSubscriptionOrderEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        ActionResult<SubscriptionOrderEvent> actionResult = action.execute();

        SubscriptionOrderEvent event = actionResult.getEntity();
        User user = event.getCreator();

        Profile profile = userService.createProfile(user);

        // return result XML
        EventResult result = new EventResult();
        result.setAccountIdentifier("appdirect-challenge");
        result.setMessage("Welcome to AppDirect!");
        result.setSuccess(profile != null);

        return result;
    }

    @RequestMapping("/subscription/cancel")
    public EventResult cancelSubscription(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetSubscriptionCancelEventAction action = new GetSubscriptionCancelEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        SubscriptionCancelEvent event = action.execute().getEntity();

        //TODO: delete all accounts in system tied to this accountId

        // return result XML
        EventResult result = new EventResult();
        result.setMessage(event.toString());
        result.setSuccess(true);

        return result;
    }

    @RequestMapping("/user/assign")
    public EventResult assignUser(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetUserAssignedEventAction action = new GetUserAssignedEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        UserAssignedEvent event = action.execute().getEntity();
        User user = event.getPayload().getUser();

        Profile profile = userService.createProfile(user);

        // return result XML
        EventResult result = new EventResult();
        result.setMessage(event.toString());
        result.setSuccess(profile != null);

        return result;
    }

    @RequestMapping("/user/unassign")
    public EventResult unassignUser(HttpServletRequest request,
            @RequestParam String url,
            @RequestParam String token,
            @AuthenticationPrincipal ConsumerAuthentication authentication) {

        logRequest(request, authentication);

        // get event details
        GetUserUnassignedEventAction action = new GetUserUnassignedEventAction(appDirectClient);
        action.setUrl(url);
        action.setToken(token);
        UserUnassignedEvent event = action.execute().getEntity();

        String openId = event.getPayload().getUser().getOpenId();
        Profile profile = profileService.getByOpenID(openId);

        boolean deleted = true;
        if (profile != null) {
            profileService.delete(profile.getId());
            deleted = !profileService.exists(profile.getId());
        }

        // return result XML
        EventResult result = new EventResult();
        result.setMessage(event.toString());
        result.setSuccess(deleted);

        return result;
    }

    private void logRequest(HttpServletRequest request, ConsumerAuthentication authentication) {
        if (log.isDebugEnabled()) {
            log.debug("request = " + request.getRequestURI() + "?" + request.getQueryString());
            if (authentication != null) {
                log.debug("authenticated = " + authentication.isAuthenticated());
                log.debug("signature validated = " + authentication.isSignatureValidated());
            } else {
                log.debug("Request not authenticated");
            }
        }
    }
    
    @RequestMapping(value = "/status")
    public String status() {return "working";}
}
