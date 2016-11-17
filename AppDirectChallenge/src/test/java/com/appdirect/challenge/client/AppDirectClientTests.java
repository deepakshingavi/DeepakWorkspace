package com.appdirect.challenge.client;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@RunWith(JUnit4.class)
public class AppDirectClientTests {

    private final static String CONSUMER_KEY = "appdirectbackend1-140762";
    private final static String CONSUMER_SECRET = "rlOZqIxLr8tQrCSN";

    public static final String TEST_URL = "https://www.appdirect.com/rest/api/events/dummyOrder";

    @InjectMocks
    private AppDirectClient client;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpEntity httpEntity;

    @Before
    public void setup() {
        this.client = new AppDirectClient(CONSUMER_KEY, CONSUMER_SECRET);

        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoConsumerKeyThrowsException() {
        new AppDirectClient(null, CONSUMER_SECRET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoConsumerSecretThrowsException() {
        new AppDirectClient(CONSUMER_KEY, null);
    }

    @Test
    public void testSignUrl() throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String originalUrl = "https://www.appdirect.com/rest/api/events/dummyChange";
        String signedUrl = client.signUrl(originalUrl);

        assertThat(signedUrl, is(notNullValue()));
        assertThat(signedUrl, containsString(originalUrl));
        assertThat(signedUrl, containsString("oauth_consumer_key=" + CONSUMER_KEY));
        assertThat(signedUrl, containsString("oauth_nonce"));
        assertThat(signedUrl, containsString("oauth_signature"));
        assertThat(signedUrl, containsString("oauth_signature_method=HMAC-SHA1"));
        assertThat(signedUrl, containsString("oauth_timestamp"));
        assertThat(signedUrl, containsString("oauth_version=1.0"));
    }
   
}
