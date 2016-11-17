package com.appdirect.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration // spring-boot auto-configuration
@EnableConfigurationProperties
@ComponentScan
public class AppInit {
    public static void main(String[] args) {
        SpringApplication.run(AppInit.class, args);
    }
}
