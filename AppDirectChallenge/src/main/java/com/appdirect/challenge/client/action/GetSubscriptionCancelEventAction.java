package com.appdirect.challenge.client.action;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.domain.appdirect.event.subscription.SubscriptionCancelEvent;

public class GetSubscriptionCancelEventAction extends GetEventAction {

    public GetSubscriptionCancelEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<SubscriptionCancelEvent> execute() {
        return execute(SubscriptionCancelEvent.class);
    }
}
