package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceMockTest {

    private static final Integer CITY_FROM = 1;
    private static final Integer CITY_TO = 6;


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


    private  static final LocalDate START_DATE = LocalDate.of(2019, 01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019, 12,12);

    @Test
    void findAll(){
        Mockito.when(ticketDao.findAll()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(ticketDao).findAll();
    }

    @Test
    void findById(){
        int id = 1;
        Mockito.when(ticketDao.findById(id)).thenReturn(Optional.of(createFixture()));

        Ticket ticket = ticketService.findById(id);

        assertNotNull(ticket);
        assertEquals(CITY_FROM, ticket.getTicketDirectionFrom());
        assertEquals(CITY_TO, ticket.getTicketDirectionTo());

        Mockito.verify(ticketDao).findById(id);

    }

    @Test
    void update(){
        ticketService.update(createFixture());

        Mockito.verify(ticketDao).update(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();
        assertNotNull(ticket);
        assertEquals(ticket.getTicketDirectionFrom(), CITY_FROM);
        assertEquals(ticket.getTicketDirectionTo(), CITY_TO);

    }

    @Test
    void delete(){
        int id = 2;

        ticketService.delete(id);

        Mockito.verify(ticketDao).delete(id);

    }

    @Test
    void add(){
        Ticket ticket = createFixture();
        Mockito.when(ticketDao.add(ticket)).thenReturn(ticket);

        Ticket newTicket = ticketService.add(ticket);

        assertNotNull(newTicket);
        assertEquals(newTicket.getTicketDirectionFrom(), CITY_FROM);
        assertEquals(newTicket.getTicketDirectionTo(), CITY_TO);
        Mockito.verify(ticketDao, Mockito.times(1)).add(ticket);

    }

    @Test
    void searchTicket() {
        Ticket ticket = createFixture();

        Mockito.when(ticketDao.searchTicket(START_DATE, FINISH_DATE,
                ticket.getTicketDirectionFrom(), ticket.getTicketDirectionTo()))
                .thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.searchTicket(START_DATE, FINISH_DATE,
                ticket.getTicketDirectionFrom(), ticket.getTicketDirectionTo());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() - 1, 0);

        Mockito.verify(ticketDao).searchTicket(START_DATE, FINISH_DATE,
                ticket.getTicketDirectionFrom(), ticket.getTicketDirectionTo());
    }

    @Test
    void findAllWithDirection() {
        Mockito.when(ticketDao.findAllWithDirection()).thenReturn(Collections.singletonList(createFixture()));

        List<Ticket> result = ticketService.findAllWithDirection();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size() -1, 0);

        Mockito.verify(ticketDao).findAllWithDirection();
    }


    private Ticket createFixture(){
        Ticket ticket = new Ticket();
        ticket.setTicketDirectionFrom(CITY_FROM);
        ticket.setTicketDirectionTo(CITY_TO);
        return ticket;
    }


}
