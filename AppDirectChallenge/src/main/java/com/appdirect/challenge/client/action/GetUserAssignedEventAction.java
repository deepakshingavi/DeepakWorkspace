package com.appdirect.challenge.client.action;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.domain.appdirect.event.access.UserAssignedEvent;

public class GetUserAssignedEventAction extends GetEventAction {

    public GetUserAssignedEventAction(final AppDirectClient client) {
        super(client);
    }

    public ActionResult<UserAssignedEvent> execute() {
        return super.execute(UserAssignedEvent.class);
    }
}
