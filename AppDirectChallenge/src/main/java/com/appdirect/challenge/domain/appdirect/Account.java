package com.appdirect.challenge.domain.appdirect;

public class Account {
    private Long accountIdentifier;
    private String status;
    
    public Long getAccountIdentifier() {
		return accountIdentifier;
	}
    public void setAccountIdentifier(Long accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
    
    public String getStatus() {
		return status;
	}
    public void setStatus(String status) {
		this.status = status;
	}
}
