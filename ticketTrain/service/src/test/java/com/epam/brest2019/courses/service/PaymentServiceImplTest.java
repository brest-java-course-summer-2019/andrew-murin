package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-service.xml","classpath*:test-db.xml"})
public class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void findAll(){
        List<Payment> payments = paymentService.findAll();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    void findByTicketId(){
        int id = 2;
        List<Payment> payment = paymentService.findByTicketId(id);

        assertNotNull(payment);
        assertFalse(payment.isEmpty());
    }

    @Test
    void findById(){
        int id = 2;
        Payment payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals(2, (int)create().getTicketId());
    }

    @Test
    void add(){
        long sizeBefore = paymentService.findAll().size();
        paymentService.add(create());
        long sizeAfter = paymentService.findAll().size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    void update(){
        int id = 2;
        Payment payment = create();
        payment.setPaymentId(id);
        paymentService.update(payment);
        payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals((int)payment.getTicketId(),2 );
    }

    @Test
    void delete(){
        int id = 3;
        paymentService.delete(id);
        assertThrows(RuntimeException.class, () -> paymentService.findById(id));
    }

    private Payment create(){
        Payment payment = new Payment();
        payment.setTicketId(2);
        return payment;
    }
}
