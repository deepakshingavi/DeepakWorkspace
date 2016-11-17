package com.appdirect.challenge.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.challenge.domain.appdirect.event.Event;
import com.appdirect.challenge.domain.appdirect.event.EventType;

@XmlRootElement(name = "event")
public class SubscriptionCancelEvent extends Event {

    private SubscriptionCancelPayload payload;

    public SubscriptionCancelEvent() {
        super(EventType.SUBSCRIPTION_CANCEL);
    }


}
