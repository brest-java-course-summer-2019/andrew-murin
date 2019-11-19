package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class TicketRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private TicketRestConsumer ticketRestConsumerTest;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        initMocks(this);
        ticketRestConsumerTest = new TicketRestConsumer("url", mockRestTemplate);
    }

    @Test
    void findAll() {
        List<Ticket> tickets = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Ticket> ticket = ticketRestConsumerTest.findAll();

        Assert.assertEquals(tickets,ticket);
    }

    @Test
    void findAllWithDirection() {
        List<Ticket> tickets = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/find-all-with-direction", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Ticket> ticket = ticketRestConsumerTest.findAllWithDirection();

        Assert.assertEquals(tickets,ticket);
    }

    @Test
    void findById() {
        Ticket tickets = createTicketFixture(1);

        Mockito.when(mockRestTemplate.getForEntity("url/" + 1, Ticket.class)).thenReturn(new ResponseEntity<>(createTicketFixture(1), HttpStatus.OK));

        Ticket ticket = ticketRestConsumerTest.findById(1);

        assertEquals(tickets.getTicketId(), ticket.getTicketId());
        assertEquals(tickets.getFromCity().getCityId(), ticket.getFromCity().getCityId());
        assertEquals(tickets.getToCity().getCityId(), ticket.getToCity().getCityId());
    }

    @Test
    void add() {
        createTicketFixture(1);
        Mockito.when(mockRestTemplate.postForEntity("url", ticket, Ticket.class))
                .thenReturn(new ResponseEntity<>(createTicketFixture(1), HttpStatus.OK));

        ticketRestConsumerTest.add(ticket);
    }
//
//    @Test
//    void delete() {
//
//        ticketRestConsumerTest.delete(1);
//
//        Mockito.verify(mockRestTemplate).delete("url/" + 1);
//    }

    @Test
    void update() {
        createTicketFixture(1);
        ticketRestConsumerTest.update(ticket);

        Mockito.verify(mockRestTemplate).put("url", ticket);
    }


    private Ticket createTicketFixture(int id) {
        Ticket ticket = new Ticket();
        City city = new City();

        ticket.setTicketId(id);

        city.setCityId(1);
        ticket.setFromCity(city);

        city.setCityId(2);
        ticket.setToCity(city);

        ticket.setTicketCost(new BigDecimal(25));
        ticket.setTicketDate(LocalDate.now());

        return ticket;
    }


}
