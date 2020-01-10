package com.epam.brest2019.courses.rest_app.wiremock;

import com.epam.brest2019.courses.model.Payment;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.http.HttpStatus;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentRestControllerWireMockTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089));

    @Test
    public void whenRequestGetPayments() throws IOException {
        ResponseEntity payments = restTemplate.getForEntity("http://localhost:8088/payments", String.class);

//        String body = payments.getBody().toString();

        wireMockRule.stubFor(get(urlMatching("/payments"))
            .willReturn(aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withBody(String.valueOf(payments.getBody()))));



        assertEquals(payments.getStatusCodeValue(), 200);
        assertNotNull(payments.getBody());
        assertEquals(String.valueOf(payments.getBody()), FileUtils.readFileToString(FileUtils.toFile(TestUtils.class.getClassLoader().getResource("mapping/payments.json")), String.valueOf(StandardCharsets.UTF_8)));

    }

    private String getJSON(String path) throws IOException {
        Resource resource = applicationContext.getResource("classpath:" + path);
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }






















}
