//package com.epam.brest2019.courses.model;
//
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//public class PaymentTest {
//
//    private static final Integer NUMBER = 15;
//    private static final BigDecimal SUMM= new BigDecimal(10);
//    private static final LocalDate DATE = LocalDate.of(2019, 7, 31);
//
//    Payment payment = new Payment();
//
//    @Test
//    public void getPaymentId(){
//        payment.setPaymentId(NUMBER);
//        Assert.assertEquals(payment.getPaymentId(), NUMBER);
//
//    }
//
//    @Test
//    public void getDate() {
//        payment.setPaymentDate(DATE);
//        Assert.assertEquals(payment.getPaymentDate(), DATE);
//    }
//
//    @Test
//    public void getTicketId(){
//        payment.setTicketId(NUMBER);
//        Assert.assertEquals(payment.getTicketId(), NUMBER);
//    }
//
//    @Test
//    public void getTicketCost() {
//        payment.setTicketCost(SUMM);
//        Assert.assertEquals(payment.getTicketCost(), SUMM);
//    }
//
//    @Test
//    public void getTicketCount() {
//        payment.setTicketCount(NUMBER);
//        Assert.assertEquals(payment.getTicketCount(), NUMBER);
//    }
//
//    @Test
//    public void getTotalCost() {
//        payment.setTotalCost(SUMM);
//        Assert.assertEquals(payment.getTotalCost(), SUMM);
//    }
//
//}
