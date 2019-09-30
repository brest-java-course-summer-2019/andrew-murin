package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TicketRestConsumer implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public TicketRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Ticket add(Ticket ticket) {
        LOGGER.debug("add({})", ticket);
        ResponseEntity<Ticket> responseEntity = restTemplate.postForEntity(url, ticket, Ticket.class);
        return responseEntity.getBody();
    }

    @Override
    public void update(Ticket ticket) {
        LOGGER.debug("update({})", ticket);
        restTemplate.put(url, ticket);
    }

    @Override
    public void delete(Integer ticketId) {
        LOGGER.debug("delete({})", ticketId);
        restTemplate.delete(url + "/" + ticketId);
    }

    @Override
    public List<Ticket> findAll() {
        LOGGER.debug("findAll({})");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return  (List<Ticket>) responseEntity.getBody();
    }

    @Override
    public Ticket findById(Integer ticketId) {
        LOGGER.debug("findByID({})", ticketId);
        ResponseEntity<Ticket> responseEntity = restTemplate.getForEntity(url + "/" + ticketId, Ticket.class);
        return responseEntity.getBody();
    }
}
