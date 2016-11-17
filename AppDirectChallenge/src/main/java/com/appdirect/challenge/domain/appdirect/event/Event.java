package com.appdirect.challenge.domain.appdirect.event;

import com.appdirect.challenge.domain.appdirect.Marketplace;
import com.appdirect.challenge.domain.appdirect.User;

public abstract class Event {
    private EventType type;
    private Marketplace marketplace;
    private User creator;

    public Event(EventType type) {
        this.type = type;
    }

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
    
    
}
