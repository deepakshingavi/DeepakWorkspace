package com.appdirect.challenge.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.challenge.domain.appdirect.event.Event;
import com.appdirect.challenge.domain.appdirect.event.EventType;

@XmlRootElement(name = "event")
public class SubscriptionOrderEvent extends Event {

    private SubscriptionOrderPayload payload;

    public SubscriptionOrderEvent() {
        super(EventType.SUBSCRIPTION_ORDER);
    }

	public SubscriptionOrderPayload getPayload() {
		return payload;
	}

	public void setPayload(SubscriptionOrderPayload payload) {
		this.payload = payload;
	}
    
    
}
