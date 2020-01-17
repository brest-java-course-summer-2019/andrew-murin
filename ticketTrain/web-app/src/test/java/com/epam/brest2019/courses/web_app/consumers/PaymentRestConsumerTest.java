package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.web_app.consumers.config.ConsumerConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);

    @Before
    public void setUp() {
        paymentRestConsumerTest = new PaymentRestConsumer(LOCAL_HOST + PORT, restTemplate);
    }

    @Test
    public void findAll() throws IOException {

        wireMockRule.stubFor(get("/payments")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(getJSON(PAYMENTS_JSON))));

//        List<Payment> payments = paymentRestConsumerTest.findAll();
//        ResponseEntity<String> payments = restTemplate.getForEntity(LOCAL_HOST + PORT + "/payments", String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Payment> paymentList = mapper.convertValue(paymentRestConsumerTest.findAll(),
                new TypeReference<List<Payment>>(){
                }
        );

        assertEquals(paymentList.size(), 2);

        List<Payment> payment = new PaymentRestConsumer(LOCAL_HOST + 8088, restTemplate).findAll();
        assertEquals(payment.size(), 2);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments")));
    }

//    @Test
//    public void findAllWithDirection() {
//
//        wireMockRule.stubFor(get("/find-all-with-direction")
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        List<Payment> payment = paymentRestConsumerTest.findAllWitchDirection();
//
//        wireMockRule.verify(getRequestedFor(urlEqualTo("/find-all-with-direction")));
////        Assert.assertEquals(payments, payment);
//    }
//
//    @Test
//    public void findById() {
//
//        wireMockRule.stubFor(get("/3")
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//
//        Payment payment = paymentRestConsumerTest.findById(3);
//
//        wireMockRule.verify(getRequestedFor(urlEqualTo("/3")));
//    }
//
//    @Test
//    public void add() {
//        wireMockRule.stubFor(post("/")
//                .willReturn((aResponse()
//                                .withStatus(HttpStatus.SC_CREATED))));
//
//        paymentRestConsumerTest.add(payment);
//
//        wireMockRule.verify(postRequestedFor(urlEqualTo("/")));
//    }
//
//    @Test
//    public void update() {
//
//        wireMockRule.stubFor(put("/")
//                .willReturn((aResponse()
//                                .withStatus(HttpStatus.SC_ACCEPTED))));
//
//
//        paymentRestConsumerTest.update(payment);
//
//        wireMockRule.verify(putRequestedFor(urlEqualTo("/")));
//    }
//
//
//    @Test
//    public void deletePayment() {
//
//        wireMockRule.stubFor(delete("/1")
//                .willReturn((aResponse()
//                        .withStatus(HttpStatus.SC_NO_CONTENT))));
//
//        paymentRestConsumerTest.delete(1);
//
//        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/1")));
//    }
//
//    @Test
//    public void searchByDate() {
//
//        wireMockRule.stubFor(get("/payments/2019-01-01/2019-12-12")
//                .willReturn(aResponse()
//                                .withStatus(HttpStatus.SC_OK)));
//
//        List<Payment> payment = paymentRestConsumerTest.searchByDate(START_DATE, FINISH_DATE);
////               assertEquals(2, payment.size());
////        ResponseEntity payments = restTemplate
////                .getForEntity(LOCAL_HOST + PORT + "/2019-01-01/2019-12-12", String.class);
////        assertEquals(payments.getBody(), "eyey");
//
//
//        wireMockRule.verify(getRequestedFor(urlEqualTo("/payments/2019-01-01/2019-12-12")));
//    }

//    private ObjectMapper mapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        List<Payment> paymentList = mapper.convertValue(paymentRestConsumerTest.findAll(),
//                new TypeReference<List<Payment>>(){
//                }
//        );
//        return
//    }

    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }

}
