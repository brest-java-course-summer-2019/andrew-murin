package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
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

        assertEquals(tickets,ticket);
    }

    @Test
    void findAllWithDirection() {
        List<Ticket> tickets = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/all", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Ticket> ticket = ticketRestConsumerTest.findAllWithDirection();

        assertEquals(tickets,ticket);
    }

    @Test
    void findById() {
        Ticket tickets = createTicketFixture(1);

        Mockito.when(mockRestTemplate.getForEntity("url/" + 1, Ticket.class)).thenReturn(new ResponseEntity<>(createTicketFixture(1), HttpStatus.OK));

        Ticket ticket = ticketRestConsumerTest.findById(1);

        assertEquals(tickets.getTicketId(), ticket.getTicketId());
        assertEquals(tickets.getTicketDirectionFrom(), ticket.getTicketDirectionFrom());
        assertEquals(tickets.getTicketDirectionTo(), ticket.getTicketDirectionTo());
    }

    @Test
    void add() {
        createTicketFixture(1);
        Mockito.when(mockRestTemplate.postForEntity("url", ticket, Ticket.class))
                .thenReturn(new ResponseEntity<>(createTicketFixture(1), HttpStatus.OK));

        ticketRestConsumerTest.add(ticket);
    }

    @Test
    void delete() {

        ticketRestConsumerTest.delete(1);

        Mockito.verify(mockRestTemplate).delete("url/" + 1);
    }

    @Test
    void update() {
        createTicketFixture(1);
        ticketRestConsumerTest.update(ticket);

        Mockito.verify(mockRestTemplate).put("url", ticket);
    }


    private Ticket createTicketFixture(int id) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ticket.setTicketDirectionFrom(1 + id);
        ticket.setTicketDirectionTo(2 + id);
        ticket.setTicketCost(new BigDecimal(25));
        ticket.setTicketDate(LocalDate.now());
        return ticket;
    }


}
