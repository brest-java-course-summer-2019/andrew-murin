package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.model.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceMockTest {

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    @AfterEach
    void after(){
        Mockito.verifyNoMoreInteractions(paymentDao);
    }

    @Test
    void findAll(){
        Mockito.when(paymentDao.findAll()).thenReturn(Collections.singletonList(create()));

        List<Payment> result = paymentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(paymentDao).findAll();
    }

    @Test
    void findByTicketId(){
        int id = 2;

        Mockito.when(paymentDao.findByTicketId(id)).thenReturn(Collections.singletonList(create()));
        List<Payment> result = paymentService.findByTicketId(id);

        assertNotNull(result);
        assertTrue(result.size() > 0);

        Mockito.verify(paymentDao).findByTicketId(id);
    }

    @Test
    void findById(){
        int id = 2;
        Mockito.when(paymentDao.findById(id)).thenReturn(Optional.of(create()));

        Payment payment = paymentService.findById(id);
        LocalDate time = LocalDate.of(2019, 2, 7);
        payment.setLocalDate(time);

        assertNotNull(payment);
        assertEquals(time, payment.getLocalDate());

        Mockito.verify(paymentDao).findById(id);
    }

    @Test
    void add(){
        Payment payment = create();

        Mockito.when(paymentDao.add(payment)).thenReturn(create());
        Payment newPayment = paymentService.add(payment);

        assertNotNull(newPayment);
        assertEquals(payment.getLocalDate(), newPayment.getLocalDate());

        Mockito.verify(paymentDao, Mockito.times(1)).add(payment);
    }

    @Test
    void update(){
        LocalDate localDate  = LocalDate.of(2019, 2,7);
        paymentService.update(create());
        Mockito.verify(paymentDao).update(paymentCaptor.capture());

        Payment payment = paymentCaptor.getValue();
        assertNotNull(payment);
        assertEquals(localDate, payment.getLocalDate());
        assertEquals(1, (int) payment.getPaymentId());
    }

    @Test
    void delete(){
        int id = 3;
        paymentService.delete(id);

        Mockito.verify(paymentDao, Mockito.times(1)).delete(id);
    }

    private Payment create(){
        Payment payment = new Payment();
        payment.setLocalDate(LocalDate.of(2019, 2, 7));
        payment.setTicketId(2);
        payment.setPaymentId(1);
        return payment;
    }

}
