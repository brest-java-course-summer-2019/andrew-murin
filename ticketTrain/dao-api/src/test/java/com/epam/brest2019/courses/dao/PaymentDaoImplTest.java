package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.dao.config.DaoConfig;
import com.epam.brest2019.courses.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentDaoImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoImplTest.class);

    @Autowired
    private PaymentDao paymentDao;

    private Payment payment;

    @BeforeEach
    void changes(){
        LocalDate localDate = LocalDate.now();
        payment = new Payment(localDate, 1);

        paymentDao.add(payment);

        int lastElement = paymentDao.findAll().size();
        payment = paymentDao.findById(lastElement);
    }


    @Test
    void findAllPayment(){
        LOGGER.info("findAllPayment {}", Payment.class);
        List<Payment> payments = paymentDao.findAll();
        assertNotNull(paymentDao);
        assertTrue(payments.size() > 0);
    }

    @Test
    void findByTicketId(){
        List<Payment> payments = paymentDao.findByTicketId(1);
        assertNotNull(paymentDao);
        assertTrue(payments.size() > 0);
    }

    @Test
    void findByPaymentId(){
        LOGGER.info("findAllPayment {}", payment);
        Payment newPayment = paymentDao.findById(payment.getPaymentId());

        assertNotNull(paymentDao);
        assertEquals(newPayment.getPaymentId(), payment.getPaymentId());
        assertEquals(newPayment.getPaymentDate(), LocalDate.now());
        assertEquals(newPayment.getTicketId(), payment.getTicketId());
    }

    @Test
    void update(){
        LocalDate localDate = LocalDate.of(2019, 2, 7);
        payment.setPaymentDate(localDate);
        payment.setTicketId(1);
        paymentDao.update(payment);

        Payment updatePayment =  paymentDao.findById(payment.getPaymentId());
        assertEquals(updatePayment.getPaymentId(), payment.getPaymentId());
        assertEquals(updatePayment.getPaymentDate(), payment.getPaymentDate());
        assertEquals(updatePayment.getTicketId(), payment.getTicketId());
    }

    @Test
    void delete(){
        paymentDao.add(payment);
        List<Payment> payments = paymentDao.findAll();

        int sizeBefore = payments.size();

        paymentDao.delete(payment.getPaymentId());

        assertEquals((sizeBefore) - 1, paymentDao.findAll().size());
    }

    @Test
    void findAllWithDirection() {
        List<Payment> payments = paymentDao.findAllWitchDirection();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
        assertTrue(payments.size() > 0);
    }

    @Test
    void searchByDate() {
        LocalDate startDate = LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019,12,12);

        List<Payment> payments = paymentDao.searchByDate(startDate, finishDate);

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }
}
