package com.appdirect.challenge.client.action;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.domain.appdirect.event.subscription.SubscriptionOrderEvent;

public class GetSubscriptionOrderEventAction extends GetEventAction {

    public GetSubscriptionOrderEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<SubscriptionOrderEvent> execute() {
        return execute(SubscriptionOrderEvent.class);
    }
}
