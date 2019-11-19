package com.epam.brest2019.courses.service.implTest;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.service.config.ServiceConfig;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Sql("classpath:data.sql")
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "h2-database")
@ContextConfiguration(classes = {ServiceConfig.class, DataSourceConfig.class})
public class TicketServiceImplTest {

    private final static Integer CITY_FROM = 1;
    private final static Integer CITY_TO = 3;

    @Autowired
    private TicketService ticketService;

    @Test
    public void findAll(){
        List<Ticket> tickets = ticketService.findAll();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }


    @Test
    public void findById(){
        int id = 2;
        Ticket ticket = ticketService.findById(id);


        assertNotNull(ticket);
        assertEquals(id, (int) ticket.getTicketId());
    }

    @Test
    public void update(){
        int id = 3;
        Ticket ticket = createFixture();
        City city = new City();

        ticket.setTicketId(id);

        city.setCityId(CITY_FROM);
        ticket.setFromCity(city);

        assertEquals(CITY_FROM, ticket.getFromCity().getCityId());

        city.setCityId(CITY_TO);
        ticket.setToCity(city);

        ticketService.update(ticket);
        ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals(CITY_TO, ticket.getToCity().getCityId());
    }

    @Test
    public void delete(){
        Ticket ticket = createFixture();

        ticketService.add(ticket);
        int sizeBefore = ticketService.findAll().size();

        ticketService.delete(ticket);
        int sizeAfter = ticketService.findAll().size();

        assertTrue(sizeBefore > sizeAfter);

    }

    @Test
    public void add(){
        int sizeBefore = ticketService.findAll().size();

        Ticket ticket = createFixture();

        ticketService.add(ticket);
        int sizeAfter = ticketService.findAll().size();

        assertTrue(sizeBefore  < sizeAfter);
    }

    @Test
    public void searchTicket() {

        LocalDate startDate = LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019,12,12);

        List<Ticket> tickets = ticketService.searchTicket(startDate,finishDate,CITY_FROM, CITY_TO);

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }

    @Test
    public void findAllWithDirection() {
        List<Ticket> tickets = ticketService.findAllWithDirection();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }


    private Ticket createFixture(){
        Ticket ticket = new Ticket();
        City city = new City();

        city.setCityId(CITY_FROM);
        ticket.setFromCity(city);

        city.setCityId(CITY_TO);
        ticket.setToCity(city);

        ticket.setTicketCost(new BigDecimal(50));
        ticket.setTicketDate(LocalDate.now());

        return ticket;
    }

}
