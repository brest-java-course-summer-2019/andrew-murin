package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class PaymentRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private PaymentRestConsumer paymentRestConsumerTest;

    private Payment payment;

    @BeforeEach
    void setUp() {
        initMocks(this);
        paymentRestConsumerTest = new PaymentRestConsumer("url", mockRestTemplate);
    }

    @Test
    void findAll() {
        List<Payment> payments = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        List<Payment> payment =  paymentRestConsumerTest.findAll();

        Assert.assertEquals(payments,payment);
    }

    @Test
    void findAllWithDirection() {
        List<Payment> payments = Arrays.asList();

        Mockito.when(mockRestTemplate.getForEntity("url/find-All-With-Direction", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        List<Payment> payment = paymentRestConsumerTest.findAllWitchDirection();

        Assert.assertEquals(payments, payment);
    }

    @Test
    void findById() {
        Payment payments = createPaymentFixture(1);

        Mockito.when(mockRestTemplate.getForEntity("url/" + 1, Payment.class))
                .thenReturn(new ResponseEntity<>(createPaymentFixture(1), HttpStatus.OK));

        Payment payment= paymentRestConsumerTest.findById(1);

        Assert.assertEquals(payments.getPaymentId(), payment.getPaymentId());
        Assert.assertEquals(payments.getPaymentDate(), payment.getPaymentDate());
        Assert.assertEquals(payments.getTicketId(), payment.getTicketId());
    }

    @Test
    void add() {
        Mockito.when(mockRestTemplate.postForEntity("url", payment, Payment.class))
                .thenReturn(new ResponseEntity<>(createPaymentFixture(1), HttpStatus.OK));

        paymentRestConsumerTest.add(payment);
    }

    @Test
    void update() {
        paymentRestConsumerTest.update(payment);

        Mockito.verify(mockRestTemplate).put("url", payment);
    }

    @Test
    void delete() {
        paymentRestConsumerTest.delete(1);

        Mockito.verify(mockRestTemplate).delete("url/" + 1);
    }

    @Test
    void searchByDate() {
        List<Payment> payments = Arrays.asList();
        LocalDate  startDate = LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019,12,12);


        Mockito.when(mockRestTemplate.getForEntity("url/" + startDate + "/" + finishDate, List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        List<Payment> payment = paymentRestConsumerTest.searchByDate(startDate, finishDate);

        Assert.assertEquals(payments, payment);
    }

    private Payment createPaymentFixture(int id) {
        Payment payment = new Payment();
        payment.setPaymentId(1);
        payment.setPaymentDate(LocalDate.now());
        payment.setTicketId(id);

        return payment;
    }
}
