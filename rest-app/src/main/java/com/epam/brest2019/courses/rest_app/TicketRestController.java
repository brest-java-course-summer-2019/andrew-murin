package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TicketRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestController.class);

    @Autowired
    private TicketService ticketService;


    @GetMapping("/tickets")
    public List<Ticket> findAll() {
        LOGGER.debug("Findall tickets");
        return ticketService.findAll();
    }

    @GetMapping("/tickets/find-all-with-direction")
    public List<Ticket> findAllWithDirection() {
        LOGGER.debug("Find all tickets");
        return ticketService.findAllWithDirection();
    }

    @GetMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket findById(@PathVariable("id") Integer id) {
        LOGGER.debug("Find ticket by id ({})", id);
        return ticketService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ticket> add(@RequestBody Ticket ticket) {
        LOGGER.debug("Add ticket ({})", ticket);
        Ticket result = ticketService.add(ticket);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping("/tickets")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody Ticket ticket) {
        LOGGER.debug("Update ticket ({})", ticket);
        ticketService.update(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public void delete(@PathVariable("id") Integer id) {
        LOGGER.debug("Delete ticket ({})", id);
        ticketService.delete(id);
    }


    @GetMapping("/tickets/{startDate}/{finishDate}/{directionFrom}/{directionTo}")
    public List<Ticket> searchTicket(@PathVariable("startDate") String startDate,
                                     @PathVariable("finishDate") String finishDate,
                                     @PathVariable("directionFrom") Integer directionFrom,
                                     @PathVariable("directionTo") Integer directionTo) {

        LOGGER.debug("Search tickets by date & directions");

        LocalDate startDateView = LocalDate.parse(startDate);
        LocalDate startFinishView = LocalDate.parse(finishDate);

        return ticketService.searchTicket(startDateView, startFinishView,directionFrom, directionTo);
    }

}
