package com.epam.brest2019.courses.model;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;


public class TicketTest {

    Ticket ticket = new Ticket();

    @Test
    public void getTicketId() {
        ticket.setTicketId(5);
        Assert.assertEquals(ticket.getTicketId().intValue(),5);
    }

    @Test
    public void getCost() {
        ticket.setCost(new BigDecimal("30"));
        Assert.assertTrue(ticket.getCost().compareTo(new BigDecimal("30")) == 0);
    }

    @Test
    public void getLocalDate() {
        LocalDate localDate = LocalDate.of(2019,07,15);
        ticket.setLocalDate(localDate);
        Assert.assertEquals(localDate, ticket.getLocalDate());
    }

    @Test
    public void getDirection() {
        ticket.setTicketDirection("Warsaw");
        Assert.assertTrue(ticket.getTicketDirection().equals("Warsaw"));
    }
}
