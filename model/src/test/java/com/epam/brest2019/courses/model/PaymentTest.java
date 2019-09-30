package com.epam.brest2019.courses.model;


import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PaymentTest {
    Payment payment = new Payment();

    @Test
    public void getPaymentId(){
        payment.setPaymentId(15);
        Assert.assertEquals(15, (int) payment.getPaymentId());

    }

    @Test
    public void getDate() {
        LocalDate localDate = LocalDate.of(2019, 7, 31);
        payment.setPaymentDate(localDate);
        Assert.assertEquals(localDate, payment.getPaymentDate());
    }

    @Test
    public void getTicketId(){
        payment.setTicketId(5);
        Assert.assertEquals(5, (int) payment.getTicketId());
    }

}
