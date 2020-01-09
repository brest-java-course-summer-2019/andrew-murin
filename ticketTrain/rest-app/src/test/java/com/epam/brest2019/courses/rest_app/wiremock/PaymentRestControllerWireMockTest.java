package com.epam.brest2019.courses.rest_app.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.http.HttpStatus;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentRestControllerWireMockTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Rule
    public WireMockClassRule wireMockClassRule = new WireMockClassRule(wireMockConfig().port(9999));

    @Test
    public void whenRequestGetPayments() {
        stubFor(get(urlEqualTo("/payments"))
            .willReturn(aResponse()
                    .withStatus(HttpStatus.SC_OK)));
    }

}
