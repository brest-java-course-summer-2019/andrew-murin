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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void findAll() throws Exception{
        List<Ticket> tickets = ticketDao.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void getTicketById() throws Exception {
        LOGGER.debug("Add Ticket: {}",Ticket.class);
        Ticket ticket = ticketDao.findById(1).get();

        assertNotNull(ticket);
        assertTrue(ticket.getTicketId().equals(1));
//        Assert.assertEquals(ticket.getDirection(),PLACE);
        assertTrue(ticket.getTicketDirection().equals(PLACE));
    }

    @Test
    public void addTicket() throws Exception {
        Ticket testTicket = new Ticket();
        //testTicket.setDirection("Warsaw");

        Ticket newTicket = ticketDao.add(testTicket);
        assertNotNull(newTicket.getTicketId());
    }

    @Test
    public void updateTicket() throws Exception {
        LOGGER.debug("Add Ticket: {}",Ticket.class);
        Ticket newTicket = new Ticket(FLIGHT);
        newTicket = ticketDao.add(newTicket);
        //newTicket.setDirection(NEW_FLIGHT);
        ticketDao.update(newTicket);
        Ticket updatedDepartment = ticketDao.findById(newTicket.getTicketId()).get();

        assertTrue(newTicket.getTicketId().equals(updatedDepartment.getTicketId()));
        assertTrue(newTicket.getTicketDirection().equals(updatedDepartment.getTicketDirection()));

    }

    @Test
    public void deleteDepartment() throws Exception {
        Ticket department = new Ticket(FLIGHT);
        department = ticketDao.add(department);
        List<Ticket> departments = ticketDao.findAll();
        int sizeBefore = departments.size();
        ticketDao.delete(department.getTicketId());
        assertTrue((sizeBefore - 1) == ticketDao.findAll().size());
    }



}

