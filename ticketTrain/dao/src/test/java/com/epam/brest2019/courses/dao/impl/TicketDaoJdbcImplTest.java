package com.epam.brest2019.courses.dao.impl;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.config.DataBaseConfig;
import com.epam.brest2019.courses.dao.config.DataSourceConfig;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
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
@ActiveProfiles(profiles = "h2-database")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, DataBaseConfig.class})
public class TicketDaoJdbcImplTest {
    //BREST
    private static final Integer CITY_FROM = 1;
    //MINSK
    private static final Integer CITY_TO = 2;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoJdbcImplTest.class);

    @Autowired
    private TicketDao ticketDao;

    private Ticket ticket;

    @Before
    public void changes() {
        ticket = new Ticket();
        City city = new City();

        ticket.setTicketDate(LocalDate.now());
        ticket.setFromCity(city);

        city.setCityId(4);
        ticket.setToCity(city);

        ticket.setTicketCost(new BigDecimal(25));
        ticketDao.add(ticket);
    }

    @After
    public void cleanChanges() {
        ticketDao.delete(ticket);
    }


    @Test
    public void findAll(){
        List<Ticket> tickets = ticketDao.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.size() > 0);
    }

    @Test
    public void findById(){
        LOGGER.debug("findById: {}",Ticket.class);
//        Ticket ticket = ticketDao.findById(1);
//
//        assertNotNull(ticket);
//        assertEquals(1, (int) ticket.getTicketId());
    }
//
    @Test
    public void add(){
        LOGGER.debug("Add Ticket: {}",Ticket.class);

        ticket = new Ticket();
        City city = new City();

        ticket.setTicketDate(LocalDate.now());
        ticket.setFromCity(city);

        city.setCityId(4);
        ticket.setToCity(city);

        ticket.setTicketCost(new BigDecimal(25));

        List<Ticket> tickets = ticketDao.findAll();
        int sizeBefore = tickets.size();

        ticketDao.add(ticket);

        assertEquals(sizeBefore + 1, ticketDao.findAll().size());
    }
//
//    @Test
//    public void updateTicket(){
//        LOGGER.debug("Add Ticket: {}",Ticket.class);
//        Ticket newTicket = new Ticket();
//        newTicket.setTicketDirectionFrom(CITY_FROM);
//        newTicket.setTicketDirectionTo(CITY_TO);
//        newTicket = ticketDao.add(newTicket);
//        //newTicket.setDirection(NEW_FLIGHT);
//        ticketDao.update(newTicket);
//        Ticket updatedTicket = ticketDao.findById(newTicket.getTicketId()).get();
//
//        assertEquals(newTicket.getTicketId(), updatedTicket.getTicketId());
//        assertEquals(newTicket.getTicketDirectionFrom(), updatedTicket.getTicketDirectionFrom());
//        assertEquals(newTicket.getTicketDirectionTo(), updatedTicket.getTicketDirectionTo());
//
//    }
//
    @Test
    public void delete(){

        List<Ticket> tickets = ticketDao.findAll();
        int sizeBefore = tickets.size();

        ticketDao.add(ticket);

        assertEquals(sizeBefore + 1, ticketDao.findAll().size());
    }
//
//    @Test
//    public void searchTicket() {
//        LocalDate startDate =  LocalDate.of(2019,01,01);
//        LocalDate finishDate = LocalDate.of(2019, 12,12);
//
//        List<Ticket> tickets = ticketDao.searchTicket(startDate, finishDate, CITY_FROM, CITY_TO);
//
//        assertNotNull(tickets);
//        assertFalse(tickets.isEmpty());
//    }
//
    @Test
    public void findAllWithDirection() {
        List<Ticket> tickets = ticketDao.findAllWithDirection();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertTrue(tickets.size() > 0);
    }



}

