package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class PaymentRestConsumerTest {

//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

//    @Autowired
    private PaymentRestConsumer paymentRestConsumerTest;

    private Payment payment;

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);

    @Before
    public void setUp() {
        paymentRestConsumerTest = new PaymentRestConsumer(LOCAL_HOST + PORT, restTemplate);
    }

    @Test
    public void findAll() {

        wireMockRule.stubFor(get(urlEqualTo("/payments"))
                .willReturn(aResponse()
                        .withStatus(200)));

        List<Payment> payments = paymentRestConsumerTest.findAll();

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments")));
    }

    @Test
    public void findAllWithDirection() {

        wireMockRule.stubFor(get(urlEqualTo("/find-all-with-direction"))
                .willReturn(aResponse()
                        .withStatus(200)));

        List<Payment> payment = paymentRestConsumerTest.findAllWitchDirection();

        wireMockRule.verify(getRequestedFor(urlEqualTo("/find-all-with-direction")));
//        Assert.assertEquals(payments, payment);
    }

    @Test
    public void findById() {

        wireMockRule.stubFor(get(urlEqualTo("/3"))
                .willReturn(aResponse()
                        .withStatus(200)));


        Payment payment = paymentRestConsumerTest.findById(3);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/3")));
    }

    @Test
    public void add() {
        wireMockRule.stubFor(post(urlEqualTo("/"))
                .willReturn((aResponse()
                                .withStatus(HttpStatus.SC_CREATED))));

        paymentRestConsumerTest.add(payment);

        wireMockRule.verify(postRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void update() {

        wireMockRule.stubFor(put(urlEqualTo("/"))
                .willReturn((aResponse()
                                .withStatus(HttpStatus.SC_ACCEPTED))));


        paymentRestConsumerTest.update(payment);

        wireMockRule.verify(putRequestedFor(urlEqualTo("/")));
    }


    @Test
    public void deletee() {

        wireMockRule.stubFor(delete(urlEqualTo("/1"))
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT))));

        paymentRestConsumerTest.delete(1);

        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/1")));
    }

    @Test
    public void searchByDate() {

        wireMockRule.stubFor(get(urlEqualTo("/2019-01-01/2019-12-12"))
                .willReturn(aResponse()
                                .withStatus(HttpStatus.SC_OK)));

        List<Payment> payment = paymentRestConsumerTest.searchByDate(START_DATE, FINISH_DATE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/2019-01-01/2019-12-12")));
    }

}
