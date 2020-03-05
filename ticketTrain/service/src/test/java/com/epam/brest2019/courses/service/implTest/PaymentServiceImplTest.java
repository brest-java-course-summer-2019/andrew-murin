package com.epam.brest2019.courses.service.implTest;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import com.epam.brest2019.courses.service.config.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);


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
        assertEquals(2, (int)createFixture().getTicketId());
    }

    @Test
    void add(){
        long sizeBefore = paymentService.findAll().size();
        paymentService.add(createFixture());
        long sizeAfter = paymentService.findAll().size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    void update(){
        int id = 2;
        Payment payment = createFixture();
        payment.setPaymentId(id);

        paymentService.update(payment);

        payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals((int)payment.getPaymentId(),2 );
    }

    @Test
    void delete(){
        int id = 3;
        paymentService.delete(id);
    }

    @Test
    void findAllWithDirection() {
        List<Payment> payments = paymentService.findAllWitchDirection();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    void searchByDate() {
        List<Payment> payments = paymentService.searchByDate(START_DATE, FINISH_DATE);

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    private Payment createFixture(){
        Payment payment = new Payment();
        payment.setTicketId(2);
        payment.setPaymentDate(LocalDate.now());
        return payment;
    }
}
