package com.epam.brest2019.courses.dao.impl;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.config.DataBaseDAOConfig;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@Sql("classpath:data.sql")
@ActiveProfiles(profiles = "mysql-database")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DataBaseDAOConfig.class})
public class TicketDaoJdbcImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoJdbcImplTest.class);

    @Autowired
    private TicketDao ticketDao;

    private Ticket ticket;

    @Before
    public void changes() {
        ticket = createFixture();
        ticketDao.add(ticket);
    }

    @After
    public void cleanChanges() {
        ticketDao.delete(ticket);
    }


    @Test
    public void findAll(){
        LOGGER.debug("findAll Ticket ({})",Ticket.class);

        List<Ticket> tickets = ticketDao.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void findById(){
        LOGGER.debug("findById Ticket ({})",Ticket.class);

        Ticket newTicket = ticketDao.findById(ticket.getTicketId());

        assertNotNull(ticketDao);
        assertEquals(newTicket.getTicketId(), ticket.getTicketId());
    }

    @Test
    public void add(){
        LOGGER.debug("Add Ticket ({})",Ticket.class);

        List<Ticket> tickets = ticketDao.findAll();
        int sizeBefore = tickets.size();

        ticketDao.add(createFixture());

        assertEquals(sizeBefore + 1, ticketDao.findAll().size());
    }

    @Test
    public void updateTicket(){
        LOGGER.debug("Update Ticket ({})",Ticket.class);

        ticket.setTicketCost(new BigDecimal(30));

        ticketDao.update(ticket);

        Ticket updateTicket = ticketDao.findById(ticket.getTicketId());

        assertEquals(updateTicket.getTicketId(), ticket.getTicketId());
        assertEquals(updateTicket.getTicketCost().intValue(), ticket.getTicketCost().intValue());
    }

    @Test
    public void delete(){
        LOGGER.debug("Delete Ticket ({})",Ticket.class);

        ticket = createFixture();

        ticketDao.add(ticket);

        List<Ticket> tickets = ticketDao.findAll();
        int sizeBefore = tickets.size();

        ticketDao.delete(ticket);

        assertEquals(sizeBefore - 1, ticketDao.findAll().size());
    }

    @Test
    public void searchTicket() {
        LOGGER.debug("searchTicket ({})",Ticket.class);

        LocalDate startDate =  LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019, 12,12);

        List<Ticket> tickets = ticketDao.searchTicket(startDate, finishDate, 1, 3);

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }

    @Test
    public void findAllWithDirection() {
        LOGGER.debug("findAllWithDirection Ticket ({})",Ticket.class);

        List<Ticket> tickets = ticketDao.findAllWithDirection();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertTrue(tickets.size() > 0);
    }


    private Ticket createFixture() {
        ticket = new Ticket();
        City city = new City();

        ticket.setTicketDate(LocalDate.now());
        ticket.setFromCity(city);

        city.setCityId(4);
        ticket.setToCity(city);

        ticket.setTicketCost(new BigDecimal(25));

        return ticket;
    }


}

