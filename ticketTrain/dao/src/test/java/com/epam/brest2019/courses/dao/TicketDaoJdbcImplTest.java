package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class TicketDaoJdbcImplTest {

    private static final String PLACE = "Warsaw";
    private static final String FLIGHT = "BREST-MOSCOW";
    private static final String NEW_FLIGHT = "BREST-PRAGA";

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoJdbcImplTest.class);

    @Autowired
    TicketDao ticketDao;

    @Test
    public void findAll(){
        List<Ticket> tickets = ticketDao.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void findById(){
        LOGGER.debug("Add Ticket: {}",Ticket.class);
        Ticket ticket = ticketDao.findById(1).get();

        assertNotNull(ticket);
        assertEquals(1, (int) ticket.getTicketId());
//        Assert.assertEquals(ticket.getDirection(),PLACE);
        assertEquals(ticket.getTicketDirection(), PLACE);
    }

    @Test
    public void addTicket(){
        Ticket testTicket = new Ticket();
        //testTicket.setDirection("Warsaw");

        Ticket newTicket = ticketDao.add(testTicket);
        assertNotNull(newTicket.getTicketId());
    }

    @Test
    public void updateTicket(){
        LOGGER.debug("Add Ticket: {}",Ticket.class);
        Ticket newTicket = new Ticket(FLIGHT);
        newTicket = ticketDao.add(newTicket);
        //newTicket.setDirection(NEW_FLIGHT);
        ticketDao.update(newTicket);
        Ticket updatedTicket = ticketDao.findById(newTicket.getTicketId()).get();

        assertEquals(newTicket.getTicketId(), updatedTicket.getTicketId());
        assertEquals(newTicket.getTicketDirection(), updatedTicket.getTicketDirection());
        assertEquals(newTicket.getTicketDirection(), updatedTicket.getTicketDirection());

    }

    @Test
    public void deleteTicket(){
        Ticket ticket = new Ticket(FLIGHT);
        ticket = ticketDao.add(ticket);
        List<Ticket> tickets = ticketDao.findAll();
        int sizeBefore = tickets.size();
        ticketDao.delete(ticket.getTicketId());
        assertEquals((sizeBefore - 1), ticketDao.findAll().size());
    }



}

