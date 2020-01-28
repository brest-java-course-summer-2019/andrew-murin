package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.web_app.consumers.config.ConsumerConfiguration;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.epam.brest2019.courses.web_app.consumers.serialize.SerializeClass.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConsumerConfiguration.class)
public class PaymentRestConsumerTest {

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

    private Payment payment;

    private PaymentRestConsumer paymentRestConsumerTest;

    @Autowired
    private RestTemplate restTemplate;

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
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_JSON))));

        List<Payment> payments = paymentRestConsumerTest.findAll();
        List<Payment> paymentList = convertMapperList(getJSON(PAYMENTS_JSON));

        assertEquals(payments,paymentList);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void findAllWithDirection() throws IOException {

        wireMockRule.stubFor(get("/find-all-with-direction")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_ALL_JSON))));

        List<Payment> payments = paymentRestConsumerTest.findAllWitchDirection();
        List<Payment> paymentList = convertMapperList(getJSON(PAYMENTS_ALL_JSON));

        assertEquals(payments, paymentList);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/find-all-with-direction")));
    }

    @Test
    public void findById() throws IOException {

        wireMockRule.stubFor(get("/1")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_BY_ID_1_JSON))));

        Payment payment = paymentRestConsumerTest.findById(1);
        Payment payments = convertMapperObject(getJSON(PAYMENTS_BY_ID_1_JSON), Payment.class);

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

        wireMockRule.stubFor(get("/1")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(PAYMENTS_BY_ID_1_JSON))));

        wireMockRule.stubFor(put("/")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE))));


        payment = paymentRestConsumerTest.findById(1);
        payment.setPaymentDate(LocalDate.of(2020, 10, 10));

        paymentRestConsumerTest.update(payment);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/1")));
        wireMockRule.verify(putRequestedFor(urlEqualTo("/")));
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
        List<Payment> paymentList = convertMapperList(getJSON(PAYMENTS_SEARCH_JSON));

        assertEquals(payment, paymentList);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/2019-01-01/2019-12-12")));
    }

    //Tests at bad case
    @Test
    public void faultPayments() {

        wireMockRule.stubFor(get(urlEqualTo("/payments/fault"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        try {
            restTemplate.getForEntity(LOCAL_HOST + PORT + "/payments/fault", Payment.class);
        } catch (Exception ignored) {
        }

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments/fault")));
    }

    @Test
    public void errorByDeletePayments() {

        wireMockRule.stubFor(get(urlEqualTo("/payments/10000000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_NOT_FOUND)));

        try {
            restTemplate.getForEntity(LOCAL_HOST + PORT + "/payments/10000000", Payment.class);
        } catch (Exception ignored) {
        }

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments/10000000")));
    }

}
