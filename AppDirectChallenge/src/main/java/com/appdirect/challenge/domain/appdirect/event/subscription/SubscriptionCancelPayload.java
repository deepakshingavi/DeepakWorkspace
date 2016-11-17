package com.appdirect.challenge.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.challenge.domain.appdirect.Account;

@XmlRootElement(name = "payload")
public class SubscriptionCancelPayload {
    Account account;
}
