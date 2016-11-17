package com.appdirect.challenge.client.action;

import com.appdirect.challenge.client.AppDirectClient;

public abstract class AppDirectAction implements IAppDirectAction {

    protected final AppDirectClient client;

    public AppDirectAction(AppDirectClient client) {
        this.client = client;
    }
    
    public AppDirectClient getClient() {
		return client;
	}
}
