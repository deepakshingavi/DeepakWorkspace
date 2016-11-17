package com.appdirect.challenge.fixture;

import java.security.Principal;

import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class AuthenticationFixtures {
    public static Principal authenticatedOpenIdToken() {
        return new OpenIDAuthenticationToken(OpenIDAuthenticationStatus.SUCCESS, "", "", null);
    }
}
