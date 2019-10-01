package com.epam.brest2019.courses.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class TicketTest {

    //BREST
    private static final Integer CITY_FROM = 1;
    //MINSK
    private static final Integer CITY_TO = 2;

    private static final String PRICE = "30";

    private static final String FROM_BREST = "BREST";

    private static final String TO_MINSK = "MINSK";


    Ticket ticket = new Ticket();


    @Test
    public void getTicketId() {
        ticket.setTicketId(5);
        Assert.assertEquals(ticket.getTicketId().intValue(),5);
    }

    @Test
    public void getTicketCost() {
        ticket.setTicketCost(new BigDecimal(PRICE));
        Assert.assertTrue(ticket.getTicketCost().compareTo(new BigDecimal(PRICE)) == 0);
    }

    @Test
    public void getLocalDate() {
        LocalDate localDate = LocalDate.of(2019,07,15);
        ticket.setTicketDate(localDate);
        Assert.assertEquals(localDate, ticket.getTicketDate());
    }

    @Test
    public void getDirectionFrom() {
        ticket.setTicketDirectionFrom(CITY_FROM);
        Assert.assertEquals(ticket.getTicketDirectionFrom(), CITY_FROM);
    }

    @Test
    public void getDirectionTo() {
        ticket.setTicketDirectionTo(CITY_TO);
        Assert.assertEquals(ticket.getTicketDirectionTo(), CITY_TO);
    }

    @Test
    public void getCityFrom() {
        ticket.setCityFrom(FROM_BREST);
        Assert.assertEquals(ticket.getCityFrom(), FROM_BREST);
    }

    @Test
    public void getCityTo() {
        ticket.setCityTo(TO_MINSK);
        Assert.assertEquals(ticket.getCityTo(), TO_MINSK);
    }
}
