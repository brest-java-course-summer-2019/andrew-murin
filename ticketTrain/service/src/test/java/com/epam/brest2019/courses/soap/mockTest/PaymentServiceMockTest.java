package com.epam.brest2019.courses.service.mockTest;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceMockTest.class);

    @Mock
    private PaymentDao paymentDao;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

//    @AfterEach
//    void after(){
//        Mockito.verifyNoMoreInteractions(paymentDao);
//    }

    private static final Integer FROM_CITY = 1;
    private static final Integer TO_CITY = 5;

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);
    private static final LocalDate TEST_DATE = LocalDate.of(2019,2,7);



    @Test
    public void findAll(){
        LOGGER.debug("findAll");

        Mockito.when(paymentDao.findAll()).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(paymentDao).findAll();
    }

    @Test
    public void findByTicketId(){
        LOGGER.debug("findByTicketId");

        int id = 2;

        Mockito.when(paymentDao.findByTicketId(id)).thenReturn(Collections.singletonList(createFixture()));
        List<Payment> result = paymentService.findByTicketId(id);

        assertNotNull(result);
        assertTrue(result.size() > 0);

        Mockito.verify(paymentDao).findByTicketId(id);
    }

    @Test
    public void findById(){
        LOGGER.debug("findById");

        int id = 2;
        Mockito.when(paymentDao.findById(id)).thenReturn(createFixture());

        Payment payment = paymentService.findById(id);
        payment.setPaymentDate(TEST_DATE);

        assertNotNull(payment);
        assertEquals(TEST_DATE, payment.getPaymentDate());

        Mockito.verify(paymentDao).findById(id);
    }

    @Test
    public void add(){
        LOGGER.debug("Add");

        Payment payment = createFixture();

        paymentService.setJavaMailSender(javaMailSender);
        paymentService.add(payment);

        Mockito.verify(paymentDao, Mockito.times(1)).add(payment);
    }

    @Test
    public void update(){
        LOGGER.debug("Update");

        paymentService.update(createFixture());
        Mockito.verify(paymentDao).update(paymentCaptor.capture());

        Payment payment = paymentCaptor.getValue();
        assertNotNull(payment);
        assertEquals(payment.getPaymentDate(), TEST_DATE);
    }

    @Test
    public void delete(){
        LOGGER.debug("Delete");
        int id = 1;

        Payment payment = paymentService.findById(id);
        paymentService.delete(id);

        Mockito.verify(paymentDao, Mockito.times(1)).delete(payment);
    }

    @Test
    public void findAllWithDirection() {
        LOGGER.debug("findAllWithDirection");

        Mockito.when(paymentDao.findAllWitchDirection()).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.findAllWitchDirection();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() - 1, 0);

        Mockito.verify(paymentDao).findAllWitchDirection();
    }

    @Test
    public void searchByDate() {
        LOGGER.debug("Search By Date");

        Mockito.when(paymentDao.searchByDate(START_DATE, FINISH_DATE)).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.searchByDate(START_DATE, FINISH_DATE);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() -1, 0);

        Mockito.verify(paymentDao).searchByDate(START_DATE, FINISH_DATE);
    }


    private Payment createFixture(){
        Payment payment = new Payment();
        Ticket ticket = new Ticket();
        City city = new City();

        ticket.setTicketId(1);
        ticket.setTicketDate(TEST_DATE);
        ticket.setTicketCost(new BigDecimal(100));

        city.setCityId(FROM_CITY);
        ticket.setFromCity(city);

        city.setCityId(TO_CITY);
        ticket.setToCity(city);

        payment.setPaymentDate(TEST_DATE);
        payment.setTicketId(ticket);

        return payment;
    }

}
