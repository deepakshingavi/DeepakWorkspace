package com.appdirect.challenge.client.action;

public interface IAppDirectAction {
   <T> ActionResult<T> execute(Class<T> resultType);
}
