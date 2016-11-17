package com.appdirect.challenge.domain.appdirect.event.access;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.challenge.domain.appdirect.event.Event;
import com.appdirect.challenge.domain.appdirect.event.EventType;

@XmlRootElement(name = "event")
public class UserUnassignedEvent extends Event {

    private AssignmentPayload payload;

    public UserUnassignedEvent() {
        super(EventType.USER_UNASSIGNED);
    }
    
    public AssignmentPayload getPayload() {
		return payload;
	}
    public void setPayload(AssignmentPayload payload) {
		this.payload = payload;
	}
}
