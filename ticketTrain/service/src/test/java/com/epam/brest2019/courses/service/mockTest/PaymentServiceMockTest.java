package com.epam.brest2019.courses.service.mockTest;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceMockTest {

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;


    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);
    private static final LocalDate TEST_DATE = LocalDate.of(2019,2,7);



    @Test
    void findAll(){
        Mockito.when(paymentDao.findAll()).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(paymentDao).findAll();
    }

    @Test
    void findByTicketId(){
        int id = 2;

        Mockito.when(paymentDao.findByTicketId(id)).thenReturn(Collections.singletonList(createFixture()));
        List<Payment> result = paymentService.findByTicketId(id);

        assertNotNull(result);
        assertTrue(result.size() > 0);

        Mockito.verify(paymentDao).findByTicketId(id);
    }

    @Test
    void findById(){
        int id = 2;
        Mockito.when(paymentDao.findById(id)).thenReturn(createFixture());

        Payment payment = paymentService.findById(id);
        payment.setPaymentDate(TEST_DATE);

        assertNotNull(payment);
        assertEquals(TEST_DATE, payment.getPaymentDate());

        Mockito.verify(paymentDao).findById(id);
    }

    @Test
    void add(){
        Payment payment = createFixture();

        paymentService.add(payment);

        Mockito.verify(paymentDao, Mockito.times(1)).add(payment);
    }

    @Test
    void update(){
        paymentService.update(createFixture());
        Mockito.verify(paymentDao).update(paymentCaptor.capture());

        Payment payment = paymentCaptor.getValue();
        assertNotNull(payment);
        assertEquals(payment.getPaymentDate(), TEST_DATE);
        assertEquals(1, (int) payment.getPaymentId());
    }

    @Test
    void delete(){
        int id = 3;
        paymentService.delete(id);

        Mockito.verify(paymentDao, Mockito.times(1)).delete(id);
    }

    @Test
    void findAllWithDirection() {
        Mockito.when(paymentDao.findAllWitchDirection()).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.findAllWitchDirection();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() -1, 0);

        Mockito.verify(paymentDao).findAllWitchDirection();
    }

    @Test
    void searchByDate() {
        Mockito.when(paymentDao.searchByDate(START_DATE, FINISH_DATE)).thenReturn(Collections.singletonList(createFixture()));

        List<Payment> result = paymentService.searchByDate(START_DATE, FINISH_DATE);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() -1, 0);

        Mockito.verify(paymentDao).searchByDate(START_DATE, FINISH_DATE);
    }


    private Payment createFixture(){
        Payment payment = new Payment();
        payment.setPaymentDate(TEST_DATE);
        payment.setTicketId(2);
        payment.setPaymentId(1);
        return payment;
    }

}
