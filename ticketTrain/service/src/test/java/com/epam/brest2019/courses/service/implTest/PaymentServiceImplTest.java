package com.epam.brest2019.courses.service.implTest;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.config.DataSourceConfig;
import com.epam.brest2019.courses.service.config.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql("classpath:data.sql")
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "h2-database")
@ContextConfiguration(classes = {ServiceConfig.class, DataSourceConfig.class})
public class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);


    @Test
    public void findAll(){
        List<Payment> payments = paymentService.findAll();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void findByTicketId(){
        int id = 2;
        List<Payment> payment = paymentService.findByTicketId(id);

        assertNotNull(payment);
        assertFalse(payment.isEmpty());
    }

    @Test
    public void findById(){
        int id = 2;
        Payment payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals(2, (int) payment.getPaymentId());
    }

    @Test
    public void add(){
        long sizeBefore = paymentService.findAll().size();
        paymentService.add(createFixture());
        long sizeAfter = paymentService.findAll().size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    public void update(){
        int id = 2;
        Payment payment = createFixture();
        payment.setPaymentId(id);

        paymentService.update(payment);

        payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals((int)payment.getPaymentId(),2 );
    }

    @Test
    public void delete(){
        int id = 7;
        Payment payment = createFixture();
        payment.setPaymentId(id);

        int size = paymentService.findAll().size();

        paymentService.delete(payment);

        assertEquals(size, paymentService.findAll().size() + 1);
    }

    @Test
    public void findAllWithDirection() {
        List<Payment> payments = paymentService.findAllWitchDirection();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void searchByDate() {
        List<Payment> payments = paymentService.searchByDate(START_DATE, FINISH_DATE);

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    private Payment createFixture(){
        Payment payment = new Payment();
        Ticket ticket = new Ticket();

        payment.setPaymentDate(LocalDate.now());
        ticket.setTicketId(1);

        payment.setTicketId(ticket);

        return payment;
    }
}
