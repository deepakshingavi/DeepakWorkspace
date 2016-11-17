package com.appdirect.challenge.client;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * The AppDirectClient handles all communication with AppDirect's APIs. It's primary purpose is to handle the low-level
 * communication details such as transport protocols and marshalling/unmarshalling.
 */
public class AppDirectClient {

	private static final Logger logger = LoggerFactory.getLogger(AppDirectClient.class);

    private OAuthConsumer oAuthConsumer;

    /**
     * Validates and create OAuth object from the set of keys.
     *
     * @param token  -  token
     * @param secret       - private key
     * @throws java.lang.IllegalArgumentException if any parameter is null
     */
    public AppDirectClient(String token, String secret) {
        if (StringUtils.isAnyBlank(token, secret)) {
            throw new IllegalArgumentException("No parameter can be null");
        }

        this.oAuthConsumer = new DefaultOAuthConsumer(token, secret);
    }

    /**
     * Signs a URL using client's OAuth credentials and performs a GET request
     *
     * @param url the unsigned URL
     * @param resultType the result type returned
     *
     * @return the result
     *
     * @throws OAuthCommunicationException
     * @throws OAuthExpectationFailedException
     * @throws OAuthMessageSignerException
     * @throws IOException
     * @throws JAXBException
     */
    public <T> T signAndGet(String url, Class<T> resultType) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException, IOException, JAXBException {
        String signedUrl = signUrl(url);

        logger.debug("signed url = " + signedUrl);

        InputStream content = doGet(signedUrl);

        T result = null;

        if (content != null) {

            if (logger.isDebugEnabled()) {
                String strContent = IOUtils.toString(content);
                logger.debug(strContent);
                content = IOUtils.toInputStream(strContent);
            }

            result = parseResponse(content, resultType);
        } else {
            logger.debug("Content from doGet() was null");
        }

        logger.debug("result is: " + result);

        return result;
    }

    /**
     * Generic method to Parse the response coming from AppDirect site
     * @param content
     * @param resultType
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	protected <T> T parseResponse(final InputStream content, final Class<T> resultType) throws JAXBException, IOException {
        T result = null;
        if (resultType.isAnnotationPresent(XmlRootElement.class)) {
            JAXBContext context = JAXBContext.newInstance(resultType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(content);
        } else if (String.class.isAssignableFrom(resultType)) {
            result = (T) IOUtils.toString(content);
        }

        return result;
    }

    /**
     * Set of instructions to execute the Get call of signed URL 
     * @param signedUrl
     * @return
     * @throws IOException
     */
    protected InputStream doGet(final String signedUrl) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(signedUrl);
        HttpResponse response = client.execute(getRequest);

        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }

    public String signUrl(String url) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        return oAuthConsumer.sign(url);
    }
}
