package com.epam.brest2019.courses.rest_app.controllers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestController.class);

    @Autowired
    private TicketService ticketService;


    @GetMapping("/tickets")
    public List<Ticket> findAll() {
        LOGGER.debug("Find all tickets");
        return ticketService.findAll();
    }


    @GetMapping("/paid-tickets")
    public List<Ticket> searchPaidTicketByDate(@RequestParam("startDate") String startDate,
                                               @RequestParam("finishDate") String finishDate) {

        LocalDateTime startDateLocal = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
        LocalDateTime finishDateLocal = LocalDateTime.of(2020, 1, 1, 1, 1, 1);

        try {
            startDateLocal = LocalDate.parse(startDate).atTime(LocalTime.now());
            finishDateLocal = LocalDate.parse(finishDate).atTime(LocalTime.now());

        } catch (Exception ex) {
            LOGGER.debug("Exception(searchPaidTicketByDate) {}", ex.getMessage());
        }

        return ticketService.searchPaidTicketByDate(startDateLocal, finishDateLocal);
    }


    @GetMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket findById(@PathVariable("id") String id) {
        LOGGER.debug("Find ticket by id ({})", id);
        return ticketService.findById(id);
    }


    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Ticket ticket) {
        LOGGER.debug("Add ticket ({})", ticket);
        ticketService.add(ticket);
    }


    @PutMapping("/tickets/{ticketId}")
    public void payTicket(@PathVariable("ticketId") String ticketId,
                          @RequestParam("paymentDate") String paymentDate,
                          @RequestParam("email") String email) {

        LOGGER.debug("Pay ticket. ticketId {}, paymentDate {}, email {})", ticketId, paymentDate, email);

        ZonedDateTime paymentDateLocal =
                LocalDate.parse(paymentDate).atStartOfDay(ZoneId.systemDefault());

        Ticket ticket = ticketService.findById(ticketId);

        ticket.setPaymentDate(paymentDateLocal);
        ticket.setEmail(email);

        ticketService.update(ticket);
    }


    @DeleteMapping("/tickets/{id}")
    public void delete(@PathVariable("id") String ticketId) {
        LOGGER.debug("Delete ticket ({})", ticketId);
        ticketService.delete(ticketId);
    }


    @PutMapping("/tickets")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody Ticket ticket) {
        LOGGER.debug("Update ticket ({})", ticket);
        ticketService.update(ticket);
    }


    @GetMapping("/search-tickets")
    public List<Ticket> searchTicket(@RequestParam("startDate") String startDate,
                                     @RequestParam("finishDate") String finishDate,
                                     @RequestParam("cityFrom") String cityFrom,
                                     @RequestParam("cityTo") String cityTo) {

        LOGGER.debug("Search tickets by date & directions");

        LocalDateTime startDateLocal = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
        LocalDateTime finishDateLocal = LocalDateTime.of(2020, 1, 1, 1, 1, 1);

        try {
            startDateLocal = LocalDate.parse(startDate).atTime(LocalTime.now());
            finishDateLocal = LocalDate.parse(finishDate).atTime(LocalTime.now());

        } catch (Exception ex) {
            LOGGER.debug("Exception(searchTicket) {}", ex.getMessage());
        }

        return ticketService.searchTicket(startDateLocal, finishDateLocal, cityFrom, cityTo);
    }
}