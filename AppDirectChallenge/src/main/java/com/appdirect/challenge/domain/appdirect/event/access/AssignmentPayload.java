package com.appdirect.challenge.domain.appdirect.event.access;

import com.appdirect.challenge.domain.appdirect.Account;
import com.appdirect.challenge.domain.appdirect.User;

public class AssignmentPayload {
    private Account account;
    private User user;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
    
}
