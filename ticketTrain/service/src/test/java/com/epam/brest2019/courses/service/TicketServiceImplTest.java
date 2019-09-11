package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class TicketServiceImplTest {

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
        assertEquals("Praga", ticket.getTicketDirection());
    }

    @Test
    void update(){
        int id = 2;
        Ticket ticket = create();
        ticket.setTicketId(id);
        ticketService.update(ticket);
        ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals("Praga", ticket.getTicketDirection());
    }

    @Test
    void delete(){
        int id = 1;
        ticketService.delete(id);
        assertThrows(RuntimeException.class, () -> ticketService.findById(id));
    }

    @Test
    void add(){
        long count = ticketService.findAll().size();
        ticketService.add(create());
        long newCount = ticketService.findAll().size();
        assertTrue(count < newCount);

    }

    private Ticket create(){
        Ticket ticket = new Ticket();
        ticket.setTicketDirection("Praga");
        return ticket;
    }

}
