package com.epam.brest2019.courses.service.implTest;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.config.ServiceConfig;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class, DataSourceConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class PaymentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImplTest.class);

    @Autowired
    private PaymentService paymentService;

    private static final LocalDate START_DATE = LocalDate.of(2018,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2020,12,12);


    @Test
    public void findAll(){
        LOGGER.debug("findAll");

        List<Payment> payments = paymentService.findAll();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void findById(){
        LOGGER.debug("findById");

        int id = 2;
        Payment payment = paymentService.findById(id);

        assertNotNull(payment);
        assertEquals(2, (int) payment.getPaymentId());
    }

    @Test
    public void add(){
        LOGGER.debug("Add");

        long sizeBefore = paymentService.findAll().size();
        paymentService.add(createFixture());
        long sizeAfter = paymentService.findAll().size();
        assertTrue(sizeBefore < sizeAfter);
    }

    @Test
    public void update(){
        LOGGER.debug("Update");

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
        LOGGER.debug("Delete");

        int id = 1;

        int size = paymentService.findAll().size();

        paymentService.delete(id);

        assertEquals(size, paymentService.findAll().size() + 1);
    }

    @Test
    public void findAllWithDirection() {
        LOGGER.debug("findAllWithDirection");
        List<Payment> payments = paymentService.findAllWitchDirection();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

    @Test
    public void searchByDate() {
        LOGGER.debug("Search by Date");

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
