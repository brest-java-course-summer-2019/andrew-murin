package com.epam.brest2019.courses.dao.impl;


import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.config.DataBaseConfig;
import com.epam.brest2019.courses.dao.config.DataSourceConfig;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@Sql("classpath:data.sql")
@ActiveProfiles(profiles = "mysql-database")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DataBaseConfig.class})
public class PaymentDaoJdbcImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoJdbcImplTest.class);

    @Autowired
    private PaymentDao paymentDao;

    private Payment payment;

//    @Before
//    public void changes(){
//        LocalDate localDate = LocalDate.of(2019, 3, 7);
//        payment = new Payment(localDate, 1);
//        payment = paymentDao.add(payment);
//    }

//    @After
//    public void cleanChanges(){
//        paymentDao.delete(payment.getPaymentId());
//    }

    @Test
    public void findAllPayment(){
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

//    @Test
//    public void add(){
//        LocalDate localDate = LocalDate.of(2019, 1, 7);
//
//        Ticket ticket = new Ticket();
//        ticket.setTicketId(1);
//
//        Payment payment = new Payment(localDate, ticket);
//        paymentDao.add(payment);
//
//        List<Payment> payments = paymentDao.findAll();
//
//        int sizeBefore = payments.size();
//
//        paymentDao.delete(payment.getPaymentId());
//        assertEquals((sizeBefore) - 1, paymentDao.findAll().size());
//    }

//    @Test
//    public void findByPaymentId(){
//        LOGGER.debug("findByPaymentId ({})", Payment.class);
//
//        Payment newPayment = paymentDao.findById(payment.getPaymentId());
//        assertNotNull(paymentDao);
//        assertEquals(newPayment.getPaymentId(), payment.getPaymentId());
//        assertEquals(newPayment.getPaymentDate(), LocalDate.now());
//        assertEquals(newPayment.getTicketId(), payment.getTicketId());
//    }
//
//    @Test
//    public void update(){
//        LocalDate localDate = LocalDate.of(2019, 2, 7);
//        payment.setPaymentDate(localDate);
//        payment.setTicketId(1);
//        paymentDao.update(payment);
//        Payment updatePayment =  paymentDao.findById(payment.getPaymentId()).get();
//        assertEquals(updatePayment.getPaymentId(), payment.getPaymentId());
//        assertEquals(updatePayment.getPaymentDate(), payment.getPaymentDate());
//        assertEquals(updatePayment.getTicketId(), payment.getTicketId());
//    }
//
//    @Test
//    public void delete(){
//        LocalDate localDate = LocalDate.of(2019, 1, 7);
//        Payment payment = new Payment(localDate, 1);
//        paymentDao.add(payment);
//        List<Payment> payments = paymentDao.findAll();
//        int sizeBefore = payments.size();
//        paymentDao.delete(payment.getPaymentId());
//        assertEquals((sizeBefore) - 1, paymentDao.findAll().size());
//    }
//
//    @Test
//    public void findAllWithDirection() {
//        List<Payment> payments = paymentDao.findAllWitchDirection();
//
//        assertNotNull(payments);
//        assertFalse(payments.isEmpty());
//        assertTrue(payments.size() > 0);
//    }
//
//    @Test
//    public void searchByDate() {
//        LocalDate startDate = LocalDate.of(2019,01,01);
//        LocalDate finishDate = LocalDate.of(2019,12,12);
//
//        List<Payment> payments = paymentDao.searchByDate(startDate, finishDate);
//
//        assertNotNull(payments);
//        assertFalse(payments.isEmpty());
//    }
}
