package com.appdirect.challenge.client.action;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.domain.appdirect.event.access.UserUnassignedEvent;

public class GetUserUnassignedEventAction extends GetEventAction {

    public GetUserUnassignedEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<UserUnassignedEvent> execute() {
        return super.execute(UserUnassignedEvent.class);
    }
}
