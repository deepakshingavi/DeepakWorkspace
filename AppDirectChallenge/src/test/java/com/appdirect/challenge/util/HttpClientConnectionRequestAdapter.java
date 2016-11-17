package com.appdirect.challenge.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpRequestBase;

public class HttpClientConnectionRequestAdapter implements oauth.signpost.http.HttpRequest {

    HttpRequestBase httpRequest;

    public HttpClientConnectionRequestAdapter(HttpRequestBase httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public String getMethod() {
        return httpRequest.getMethod();
    }

    @Override
    public String getRequestUrl() {
        return httpRequest.getURI().toString();
    }

    @Override
    public void setRequestUrl(final String url) {
        // can't do
    }

    @Override
    public void setHeader(final String name, final String value) {
        httpRequest.setHeader(name, value);
    }

    @Override
    public String getHeader(final String name) {
        Header header = httpRequest.getFirstHeader(name);
        if (header != null) {
            return header.getValue();
        }
        return null;
    }

    @Override
    public Map<String, String> getAllHeaders() {
        Header[] origHeaders = httpRequest.getAllHeaders();
        Map<String, String> headers = new HashMap<String, String>(origHeaders.length);
        for (Header header : origHeaders) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }

    @Override
    public InputStream getMessagePayload() throws IOException {
        return null;
    }

    @Override
    public String getContentType() {
        return getHeader("Content-Type");
    }

    @Override
    public HttpRequest unwrap() {
        return httpRequest;
    }
}
