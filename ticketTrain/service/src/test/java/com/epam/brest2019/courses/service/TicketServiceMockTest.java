package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TicketServiceMockTest {

    @Mock
    private TicketDao ticketDao;

    @Captor
    private ArgumentCaptor<Ticket> ticketCaptor;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @AfterEach
    void after(){
        Mockito.verifyNoMoreInteractions(ticketDao);
    }

    @Test
    void findAll(){
        Mockito.when(ticketDao.findAll()).thenReturn(Collections.singletonList(create()));

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(ticketDao).findAll();
    }

    @Test
    void findById(){
        int id = 1;
        Mockito.when(ticketDao.findById(id)).thenReturn(Optional.of(create()));

        Ticket ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals("Brest", ticket.getTicketDirection());

        Mockito.verify(ticketDao).findById(id);


    }

    @Test
    void update(){
        ticketService.update(create());

        Mockito.verify(ticketDao).update(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();
        assertNotNull(ticket);
        assertEquals("Brest", ticket.getTicketDirection());
    }

    @Test
    void delete(){
        int id = 2;

        ticketService.delete(id);

        Mockito.verify(ticketDao).delete(id);

    }

    @Test
    void add(){
        Ticket ticket = create();
        Mockito.when(ticketDao.add(ticket)).thenReturn(ticket);

        Ticket newTicket = ticketService.add(ticket);


        assertNotNull(newTicket);
        assertEquals("Brest", newTicket.getTicketDirection());
        Mockito.verify(ticketDao, Mockito.times(1)).add(ticket);

    }

    private Ticket create(){
        Ticket ticket = new Ticket();
        ticket.setTicketDirection("Brest");
        return ticket;
    }


}
