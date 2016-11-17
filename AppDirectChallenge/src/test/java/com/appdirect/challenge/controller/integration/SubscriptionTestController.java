package com.appdirect.challenge.controller.integration;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is essentially a controller that will receive all subscription events and return event XML
 */
@RestController
@RequestMapping("/test")
public class SubscriptionTestController {

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/event/createSuccess")
    public String createSubscriptionOrderEvent() throws JAXBException, IOException {
        Resource resource = context.getResource("classpath:SubscriptionOrderEvent.xml");
        return IOUtils.toString(resource.getInputStream());
    }

    @RequestMapping(value = "/event/cancelSuccess")
    public String cancelSubscriptionOrderEvent() throws JAXBException, IOException {
        Resource resource = context.getResource("classpath:SubscriptionCancelEvent.xml");
        return IOUtils.toString(resource.getInputStream());
    }
}
