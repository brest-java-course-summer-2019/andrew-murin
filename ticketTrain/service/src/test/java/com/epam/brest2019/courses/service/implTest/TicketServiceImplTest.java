package com.epam.brest2019.courses.service.implTest;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.service.config.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class TicketServiceImplTest {

    private final static Integer CITY_FROM = 2;
    private final static Integer CITY_TO = 1;

    @Autowired
    private TicketService ticketService;

    @Test
    void findAll(){
        List<Ticket> tickets = ticketService.findAll();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }

    @Test
    void findById(){
        int id = 2;
        Ticket ticket = ticketService.findById(id);


        assertNotNull(ticket);
        assertEquals(id, (int) ticket.getTicketId());
    }

    @Test
    void update(){
        int id = 3;
        Ticket ticket = createFixture();
        ticket.setTicketId(id);
        ticketService.update(ticket);
        ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals(CITY_FROM, ticket.getTicketDirectionFrom());
        assertEquals(CITY_TO, ticket.getTicketDirectionTo());
    }

    @Test
    void delete(){
        Ticket testAddTicket = createFixture();

        ticketService.add(testAddTicket);
        int sizeBeforeDelete = ticketService.findAll().size();

        ticketService.delete(sizeBeforeDelete);
        int sizeAfterDelete = ticketService.findAll().size();

        assertEquals(sizeBeforeDelete -1, sizeAfterDelete);

    }

    @Test
    void add(){
        long count = ticketService.findAll().size();
        ticketService.add(createFixture());
        long newCount = ticketService.findAll().size();
        assertTrue(count < newCount);

    }

    @Test
    void searchTicket() {

        LocalDate startDate = LocalDate.of(2019,01,01);
        LocalDate finishDate = LocalDate.of(2019,12,12);

        List<Ticket> tickets = ticketService.searchTicket(startDate,finishDate,CITY_FROM, CITY_TO);

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }

    @Test
    void findAllWithDirection() {
        List<Ticket> tickets = ticketService.findAllWithDirection();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
    }


    private Ticket createFixture(){
        Ticket ticket = new Ticket();
        ticket.setTicketCost(new BigDecimal(1));
        ticket.setTicketDate(LocalDate.now());
        ticket.setTicketDirectionFrom(2);
        ticket.setTicketDirectionTo(1);

        return ticket;
    }

}
