
package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.dao.config.DaoConfig;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TicketDaoImplTest {

    //MINSK
    private static final Integer CITY_FROM = 2;
    //BREST
    private static final Integer CITY_TO = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImplTest.class);

    @Autowired
    private TicketDao ticketDao;

    private Ticket ticket;

    @BeforeAll
    void changes(){
        ticket = new Ticket();
        ticket.setTicketCost(new BigDecimal(1));
        ticket.setTicketDate(LocalDate.now());
        ticket.setTicketDirectionFrom(2);
        ticket.setTicketDirectionTo(1);

        ticketDao.add(ticket);

        int lastElement = ticketDao.findAll().size();
        ticket = ticketDao.findById(lastElement);
    }

    @Test
    void findAll(){
        List<Ticket> tickets = ticketDao.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }

    @Test
    void findById(){
        LOGGER.debug("Add Ticket: {}",Ticket.class);
        Ticket ticket = ticketDao.findById(1);

        ticket.setTicketDirectionFrom(CITY_FROM);
        ticket.setTicketDirectionTo(CITY_TO);

        assertNotNull(ticket);
        assertEquals(1, (int) ticket.getTicketId());
        assertEquals(ticket.getTicketDirectionFrom(),(CITY_FROM));
        assertEquals(ticket.getTicketDirectionTo(),(CITY_TO));
    }

    @Test
    void addTicket(){
        int sizeBefore = ticketDao.findAll().size();

        ticketDao.add(ticket);

        int sizeAfter = ticketDao.findAll().size();

        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    void updateTicket(){
        ticketDao.add(ticket);
        ticket.setTicketDirectionTo(3);
        ticketDao.update(ticket);

        Ticket updatedTicket = ticketDao.findById(ticket.getTicketId());

        assertEquals(ticket.getTicketId(), updatedTicket.getTicketId());
        assertEquals(ticket.getTicketDirectionFrom(), updatedTicket.getTicketDirectionFrom());
        assertEquals(ticket.getTicketDirectionTo(), updatedTicket.getTicketDirectionTo());

    }

    @Test
    void deleteTicket(){
        ticketDao.add(ticket);

        int sizeBefore =  ticketDao.findAll().size();

        ticketDao.delete(ticket.getTicketId());

        assertEquals((sizeBefore - 1), ticketDao.findAll().size());
    }

    @Test
    void searchTicket() {
        LocalDate startDate =  LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019, 12,12);

        List<Ticket> tickets = ticketDao.searchTicket(startDate, finishDate, CITY_FROM, CITY_TO);

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }

    @Test
    void findAllWithDirection() {
        List<Ticket> tickets = ticketDao.findAllWithDirection();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertTrue(tickets.size() > 0);
    }
}
