package com.epam.brest2019.courses.rest_app.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentRestControllerWireMockTest {

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    private static final String PAYMENTS_JSON = "mapping/payments.json";
    private static final String PAYMENTS_ALL_JSON = "mapping/payments_all.json";
    private static final String PAYMENTS_BY_TICKET_ID_2_JSON = "mapping/payments_byTicketId_2.json";

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);


    @Test
    public void whenRequestGetPayments() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/payments"))
            .willReturn(aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withBody(getJSON(PAYMENTS_JSON))));


        ResponseEntity payments = restTemplate.getForEntity(LOCAL_HOST + PORT + "/payments", String.class);

        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments")));

    }

    @Test
    public void whenRequestGetFindPaymentsWithDirections() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/payments/find-all-with-direction"))
            .willReturn(aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withBody(getJSON(PAYMENTS_ALL_JSON))));

        ResponseEntity payments = restTemplate
                .getForEntity(LOCAL_HOST + PORT + "/payments/find-all-with-direction", String.class);


        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_ALL_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments/find-all-with-direction")));

    }


//    @Test
//    public void whenRequestOnFindPaymentsByTicketId() throws IOException {
//
//        wireMockRule.stubFor(get(urlEqualTo("/payments/ticket/2"))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.SC_OK)
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .withBody(getJSON(PAYMENTS_BY_TICKET_ID_2_JSON))));
//
//        ResponseEntity payments = restTemplate
//                .getForEntity(LOCAL_HOST + PORT + "/payments/ticket/2", String.class);
//
//
//        assertNotNull(payments.getBody());
//        assertEquals(payments.getStatusCodeValue(), 200);
//        assertEquals(payments.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
//        assertEquals(payments.getBody(), getJSON(PAYMENTS_ALL_JSON));
//
//        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments/ticket/2")));
//    }












  private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }

}
