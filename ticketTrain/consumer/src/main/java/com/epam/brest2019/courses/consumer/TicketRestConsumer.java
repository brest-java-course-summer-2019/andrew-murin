package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class TicketRestConsumer implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public TicketRestConsumer(){
    }

    public TicketRestConsumer(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

        @Override
    public void add(Ticket ticket) {
        LOGGER.debug("Add ticket ({})", ticket);
        restTemplate.postForEntity(url, ticket, Ticket.class);
    }

    @Override
    public void update(Ticket ticket) {
        LOGGER.debug("Update ticket({})", ticket);
        restTemplate.put(url, ticket);
    }

    @Override
    public void delete(Integer ticketId) {
        LOGGER.debug("Delete ticket by id({})", ticketId);
        restTemplate.delete(url + "/" + ticketId);
    }

    @Override
    public List<Ticket> find() {
        LOGGER.debug("Find  tickets");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<Ticket>) responseEntity.getBody();
    }

    @Override
    public Ticket findById(Integer ticketId) {
        LOGGER.debug("Find ticket by id({})", ticketId);
        ResponseEntity<Ticket> responseEntity = restTemplate.getForEntity(url + "/" + ticketId, Ticket.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate, Integer directionFrom, Integer directionTo) {
        LOGGER.debug("Search tickets by date & directions");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + startDate + "/" +finishDate + "/" + directionFrom + "/" + directionTo, List.class);
        return (List<Ticket>) responseEntity.getBody();
    }

    @Override
    public List<Ticket> findWithDirection() {
        LOGGER.debug("Find  with direction");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Ticket>) responseEntity.getBody();
    }


}