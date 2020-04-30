package com.epam.brest2019.courses.dao.impl;


import com.epam.brest2019.courses.dao.PaymentDao;
//import com.epam.brest2019.courses.dao.config.DataBaseDAOConfig;
import com.epam.brest2019.courses.dao.config.DataBaseDAOConfig;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@ExtendWith(SpringExtension.class)
@Import(DataBaseDAOConfig.class)
@ContextConfiguration(classes = {DataBaseDAOConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class PaymentDaoJdbcImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoJdbcImplTest.class);

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private SessionFactory sessionFactory;

    private Payment payment;

    @BeforeEach
    public void changes() {
        LocalDate localDate = LocalDate.now();

        Ticket ticket = new Ticket();
        ticket.setTicketId(1);

        payment = new Payment(localDate, ticket);
        paymentDao.add(payment);
    }

    @AfterEach
    public void cleanChanges(){
        paymentDao.delete(payment);
    }

    @Test
    public void findAllPayment() throws SQLException {
        LOGGER.debug("findAllPayment ({})", Payment.class);

        List<Payment> payments = paymentDao.findAll();
        assertNotNull(paymentDao);
        assertTrue(payments.size() > 0);
    }

    @Test
    public void findByTicketId(){
        LOGGER.debug("findByTicketId ({})", Payment.class);

        List<Payment> payments = paymentDao.findByTicketId(1);
        assertNotNull(paymentDao);
        assertTrue(payments.size() > 0);
    }

    @Test
    public void findByPaymentId(){
        LOGGER.debug("findByPaymentId ({})", Payment.class);

        Payment newPayment = paymentDao.findById(payment.getPaymentId());

        assertNotNull(paymentDao);
        assertEquals(newPayment.getPaymentId(), payment.getPaymentId());
        assertEquals(newPayment.getPaymentDate(), LocalDate.now());
        assertEquals(newPayment.getTicketId().getTicketId(), payment.getTicketId().getTicketId());
    }

    @Test
    public void add(){
        LOGGER.debug("Add Payment ({})", Payment.class);

        payment = paymentDao.findById(7);

        Payment newPayment = new Payment();

        newPayment.setPaymentDate(payment.getPaymentDate());
        newPayment.setTicketId(payment.getTicketId());
        newPayment.setTicketCost(payment.getTicketCost());
        newPayment.setTicketCount(payment.getTicketCount());

        List<Payment> payments = paymentDao.findAll();
        int sizeBefore = payments.size();

        paymentDao.add(newPayment);

        assertEquals((sizeBefore) + 1, paymentDao.findAll().size());
    }

    @Test
    public void delete(){
        LOGGER.debug("Delete Payment ({})", Payment.class);

        payment = paymentDao.findById(1);

        List<Payment> payments = paymentDao.findAll();
        int sizeAdd = payments.size();

        paymentDao.delete(payment);
        assertEquals(sizeAdd - 1, paymentDao.findAll().size());
    }

    @Test
    public void update(){
        LOGGER.debug("Update Payment({})", Payment.class);

        LocalDate localDate = LocalDate.of(2019, 2, 7);
        payment.setPaymentDate(localDate);

        paymentDao.update(payment);

        Payment updatePayment =  paymentDao.findById(payment.getPaymentId());

        assertEquals(updatePayment.getPaymentId(), payment.getPaymentId());
        assertEquals(updatePayment.getPaymentDate(), payment.getPaymentDate());
        assertEquals(updatePayment.getTicketId().getTicketId(), payment.getTicketId().getTicketId());
    }

    @Test
    public void findAllWithDirection() {
        LOGGER.debug("findAllWithDirection Payment({})", Payment.class);

        List<Payment> payments = paymentDao.findAllWitchDirection();

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
        assertTrue(payments.size() > 0);
    }

    @Test
    public void searchByDate() {
        LOGGER.debug("searchByDate Payment ({})", Payment.class);

        LocalDate startDate = LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019,12,12);

        List<Payment> payments = paymentDao.searchByDate(startDate, finishDate);

        assertNotNull(payments);
        assertFalse(payments.isEmpty());
    }

}
