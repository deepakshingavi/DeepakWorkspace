package com.appdirect.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.appdirect.challenge.AppInit;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppInit.class)
@WebAppConfiguration
@DirtiesContext
@IntegrationTest("server.port=0")
public class ApplicationTest {

    @Autowired
    WebApplicationContext ctx;

    @Test
    public void testContextLoads() {
        assertNotNull(ctx);
    }
}
