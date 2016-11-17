package com.appdirect.challenge.util;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.http.HttpRequest;

import org.apache.http.client.methods.HttpRequestBase;

public class HttpClientOAuthConsumer extends DefaultOAuthConsumer {

    public HttpClientOAuthConsumer(final String consumerKey, final String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected HttpRequest wrap(final Object request) {
        if (!(request instanceof HttpRequestBase)) {
            throw new IllegalArgumentException(
                    "The default consumer expects requests of type org.apache.http.method.HttpRequestBase");
        }
        return new HttpClientConnectionRequestAdapter((HttpRequestBase) request);
    }
}
