package com.appdirect.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.appdirect.challenge.client.AppDirectClient;
import com.appdirect.challenge.util.Constants;

@Configuration
public class AppDirectConfig {

    @Value(Constants.APPDIRECT_TOKEN)
    private String token;

    @Value(Constants.APPDIRECT_SECRET)
    private String secret;

    @Bean
    public AppDirectClient appDirectClient() {
        return new AppDirectClient(token, secret);
    }
}
