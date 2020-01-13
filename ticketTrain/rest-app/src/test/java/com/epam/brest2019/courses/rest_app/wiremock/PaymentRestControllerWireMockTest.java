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
import org.springframework.http.HttpEntity;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentRestControllerWireMockTest {

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    private static final String PAYMENTS_JSON = "mapping/payments/payments.json";
    private static final String PAYMENTS_ALL_JSON = "mapping/payments/payments_all.json";
    private static final String PAYMENTS_BY_TICKET_ID_2_JSON = "mapping/payments/payments_by_TicketId_2.json";
    private static final String PAYMENTS_BY_ID_1_JSON = "mapping/payments/payments_by_Id_1.json";
    private static final String PAYMENTS_ADD_JSON = "mapping/payments/payments_add.json";
    private static final String PAYMENTS_UPDATE_JSON = "mapping/payments/payments_update.json";
    private static final String PAYMENTS_SEARCH_JSON = "mapping/payments/payment_search.json";


    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);


    @Test
    public void whenRequestGetPayments() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/mapping/payments"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_JSON))));


        ResponseEntity payments = restTemplate.getForEntity(LOCAL_HOST + PORT + "/mapping/payments", String.class);

        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/mapping/payments")));

    }

    @Test
    public void whenRequestGetFindPaymentsWithDirections() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/mapping/payments/find-all-with-direction"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_ALL_JSON))));

        ResponseEntity payments = restTemplate
                .getForEntity(LOCAL_HOST + PORT + "/mapping/payments/find-all-with-direction", String.class);


        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_ALL_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/mapping/payments/find-all-with-direction")));

    }


    @Test
    public void whenRequestOnFindPaymentsByTicketId() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/mapping/payments/ticket/2"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_BY_TICKET_ID_2_JSON))));

        ResponseEntity payments = restTemplate
                .getForEntity(LOCAL_HOST + PORT + "/mapping/payments/ticket/2", String.class);


        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_BY_TICKET_ID_2_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/mapping/payments/ticket/2")));
    }

    @Test
    public void whenRequestOnPaymentsFindById() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/mapping/payments/1"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_BY_ID_1_JSON))));

        ResponseEntity payments = restTemplate.getForEntity(LOCAL_HOST + PORT + "/mapping/payments/1", String.class);

        assertNotNull(payments.getBody());
        assertEquals(payments.getStatusCodeValue(), 200);
        assertEquals(payments.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);
        assertEquals(payments.getBody(), getJSON(PAYMENTS_BY_ID_1_JSON));

        wireMockRule.verify(getRequestedFor(urlEqualTo("/mapping/payments/1")));
    }

    @Test
    public void whenRequestOnCreatedPayments() throws IOException {

        wireMockRule.stubFor(post(urlEqualTo("/"))
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_ADD_JSON)))));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> paymentAdd = new HttpEntity<>(getJSON(PAYMENTS_ADD_JSON), headers);

        ResponseEntity paidTicket = restTemplate.postForEntity(LOCAL_HOST + PORT + "/", paymentAdd, String.class);

        assertNotNull(paidTicket.getBody());
        assertEquals(paidTicket.getBody(), getJSON(PAYMENTS_ADD_JSON));
        assertEquals(paidTicket.getStatusCodeValue(), 201);
        assertEquals(paidTicket.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(postRequestedFor(urlEqualTo("/")));
    }


//    TODO cannot make update
//    @Test
//    public void whenRequestOnUpdatePayments() throws IOException {
//
//        wireMockRule.stubFor(put(urlEqualTo("/payments"))
//                .willReturn((aResponse()
//                        .withStatus(HttpStatus.SC_ACCEPTED)
//                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
//                        .withBody(getJSON(PAYMENTS_UPDATE_JSON)))));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> paymentUpdate = new HttpEntity<>(getJSON(PAYMENTS_UPDATE_JSON), headers);
////        Map<String, LocalDate> paymentUpdate = new HashMap<>();
////        paymentUpdate.put("paymentDate", LocalDate.now());
//
//        restTemplate.postForEntity(LOCAL_HOST + PORT + "/payments", paymentUpdate, String.class);
//
//        wireMockRule.verify(postRequestedFor(urlEqualTo("/payments")));
//    }


    @Test
    public void whenRequestOnDeletePayment() {

        wireMockRule.stubFor(delete(urlEqualTo("/mapping/payments/1"))
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT))));

        restTemplate.delete(LOCAL_HOST + PORT + "/mapping/payments/1", Payment.class);

        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/mapping/payments/1")));
    }

    @Test
    public void whenRequestOnSearchPaymentByDate() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/mapping/payments/2019-01-01/2019-12-12"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_SEARCH_JSON))));

        ResponseEntity searchPayment = restTemplate.getForEntity(LOCAL_HOST + PORT + "/mapping/payments/2019-01-01/2019-12-12", String.class);

        assertNotNull(searchPayment.getBody());
        assertEquals(searchPayment.getBody(), getJSON(PAYMENTS_SEARCH_JSON));
        assertEquals(searchPayment.getStatusCodeValue(), 200);
        assertEquals(searchPayment.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/mapping/payments/2019-01-01/2019-12-12")));
    }


    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }
}
