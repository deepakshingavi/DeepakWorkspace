package com.appdirect.challenge.domain.appdirect.event.access;

import com.appdirect.challenge.domain.appdirect.event.Event;
import com.appdirect.challenge.domain.appdirect.event.EventType;

public class UserAssignedEvent extends Event {

    private AssignmentPayload payload;

    public UserAssignedEvent() {
        super(EventType.USER_ASSIGNED);
    }
    
    public AssignmentPayload getPayload() {
		return payload;
	}
    
    public void setPayload(AssignmentPayload payload) {
		this.payload = payload;
	}
}
