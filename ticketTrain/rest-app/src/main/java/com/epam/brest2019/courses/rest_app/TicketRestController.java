package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestController.class);

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public List<Ticket> findAll(){
        LOGGER.debug("Find all");
        return ticketService.findAll();
    }

    @GetMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket getTicketById(@PathVariable Integer id){
        LOGGER.debug("Get Ticket by Id", id);
        return ticketService.findById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTicket(@RequestBody Ticket ticket){
        LOGGER.debug("Update ticket ({})", ticket);
        ticketService.update(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable("id") Integer id){
        LOGGER.debug("Delete ticket ({})", id);
        ticketService.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        LOGGER.debug("Add ticket ({})", ticket);
        Ticket result = ticketService.add(ticket);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
