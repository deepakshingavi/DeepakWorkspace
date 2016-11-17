package com.appdirect.challenge.security;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomProtectedResourceProcessingFilter extends ProtectedResourceProcessingFilter {

	private static final Logger logger = LoggerFactory.getLogger(ProtectedResourceProcessingFilter.class);

    private List<RequestMatcher> requestMatchers;

    public CustomProtectedResourceProcessingFilter(List<RequestMatcher> requestMatchers) {
        this.requestMatchers = requestMatchers;
    }

    @Override
    protected boolean requiresAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) {

        boolean matches = false;

        if (requestMatchers != null && !requestMatchers.isEmpty()) {
            for (RequestMatcher requestMatcher : requestMatchers) {
                if (requestMatcher.matches(request)) {
                    matches = true;
                    break;
                }
            }

            logger.debug("matches = " + matches);
        }

        return matches;
    }
}
