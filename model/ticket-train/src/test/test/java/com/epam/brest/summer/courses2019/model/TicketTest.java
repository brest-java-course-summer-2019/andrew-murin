package test.test.java.com.epam.brest.summer.courses2019.model;

import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;


public class TicketTest {

    com.epam.brest.summer.courses2019.model.Ticket ticket = new com.epam.brest.summer.courses2019.model.Ticket();

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
    public void getDataTime() {
        ticket.setDataTime("10/07/2019");
        Assert.assertTrue(ticket.getDataTime().equals("10/07/2019"));
    }

    @Test
    public void getDirection() {
        ticket.setDirection("Warsaw");
        Assert.assertTrue(ticket.getTicketId().equals("Warsaw"));
    }
}
