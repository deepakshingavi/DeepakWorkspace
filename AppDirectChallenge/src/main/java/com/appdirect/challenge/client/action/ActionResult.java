package com.appdirect.challenge.client.action;


public class ActionResult<T> {

    private T entity;
    private boolean success;
    private String errorMessage;
	private T event;

    public ActionResult() {}

    public ActionResult(T entity) {
        this.entity = entity;
    }

	public void setEntity(T entity) {
		this.entity=entity;
	}
	
	public T getEntity() {
		return entity;
	}

	public void setSuccess(boolean success) {
		this.success=success;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setEvent(T event) {
		this.event = event;
	}
	
	public T getEvent() {
		return event;
	}
	public boolean isSuccess() {
		return success;
	}
}
