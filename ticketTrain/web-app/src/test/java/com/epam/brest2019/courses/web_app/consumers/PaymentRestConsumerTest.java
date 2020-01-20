package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.web_app.consumers.config.ConsumerConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.http.HttpHeaders;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConsumerConfiguration.class)
public class PaymentRestConsumerTest {

    @Autowired
    private RestTemplate restTemplate;

    private PaymentRestConsumer paymentRestConsumerTest;

    private Payment payment;

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    private static final String PAYMENTS_JSON = "mapping/payments/payments.json";
    private static final String PAYMENTS_ALL_JSON = "mapping/payments/payments_all.json";
    private static final String PAYMENTS_BY_TICKET_ID_2_JSON = "mapping/payments/payments_by_TicketId_2.json";
    private static final String PAYMENTS_BY_ID_1_JSON = "mapping/payments/payments_by_Id_1.json";
    private static final String PAYMENTS_ADD_JSON = "mapping/payments/payments_add.json";
    private static final String PAYMENTS_UPDATE_JSON = "mapping/payments/payments_update.json";
    private static final String PAYMENTS_SEARCH_JSON = "mapping/payments/payment_search.json";

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @Before
    public void setUp() {
        paymentRestConsumerTest = new PaymentRestConsumer(LOCAL_HOST + PORT, restTemplate);
    }

    @Test
    public void findAll() throws IOException {

        wireMockRule.stubFor(get("/")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_JSON))));


        List<Payment> paymentList =paymentRestConsumerTest.findAll();
        List<Payment> payment = new PaymentRestConsumer(LOCAL_HOST + 8088 + "/payments", restTemplate).findAll();

        assertEquals(payment,paymentList);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void findAllWithDirection() throws IOException {

        wireMockRule.stubFor(get("/find-all-with-direction")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_ALL_JSON))));

        List<Payment> paymentList = paymentRestConsumerTest.findAllWitchDirection();
        List<Payment> payment =
                new PaymentRestConsumer(LOCAL_HOST + 8088 + "/payments", restTemplate).findAllWitchDirection();

        assertEquals(paymentList, payment);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/find-all-with-direction")));
    }

    @Test
    public void findById() throws IOException {

        wireMockRule.stubFor(get("/1")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_BY_ID_1_JSON))));


        Payment payment = paymentRestConsumerTest.findById(1);
        Payment payments =
                new PaymentRestConsumer(LOCAL_HOST + 8088 + "/payments", restTemplate).findById(1);

        assertEquals(payment, payments);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/1")));
    }

    @Test
    public void add() throws IOException {
        wireMockRule.stubFor(post("/")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_ADD_JSON)))));

        paymentRestConsumerTest.add(payment);

        wireMockRule.verify(postRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void update() throws IOException {

        wireMockRule.stubFor(put("/payments")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_ACCEPTED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_UPDATE_JSON)))));

        paymentRestConsumerTest.update(payment);

        wireMockRule.verify(putRequestedFor(urlEqualTo("/payments")));
    }

    @Test
    public void deletePayment() {

        wireMockRule.stubFor(delete("/1")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT))));

        paymentRestConsumerTest.delete(1);

        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/1")));
    }

    @Test
    public void searchByDate() throws IOException {

        wireMockRule.stubFor(get("/2019-01-01/2019-12-12")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_SEARCH_JSON))));

        List<Payment> payment = paymentRestConsumerTest.searchByDate(START_DATE, FINISH_DATE);
        List<Payment> payments = new PaymentRestConsumer(LOCAL_HOST + 8088 + "/payments", restTemplate).
                searchByDate(START_DATE, FINISH_DATE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/2019-01-01/2019-12-12")));
    }



    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }

}
