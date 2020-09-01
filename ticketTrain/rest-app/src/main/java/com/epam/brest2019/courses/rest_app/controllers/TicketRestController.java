package com.epam.brest2019.courses.rest_app.controllers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;
import com.epam.brest2019.courses.service.TicketService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Api(value = "Ticket resource")
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class TicketRestController {

    
    private final TicketService ticketService;



    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



    @ApiOperation(value = "Get cost of all paid tickets", response = BigDecimal.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully got result"),
            @ApiResponse(code = 500, message = "Internal server error ")
    })
    @GetMapping("/sum-paid-tickets")
    public TicketDto sumPaidTicketCost() {
        log.debug("Find all cost of paid tickets");
         return ticketService.sumPaidTicketCost();
    }

    @ApiOperation(value = "Find all tickets", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of tickets"),
            @ApiResponse(code = 500, message = "Internal server error ")
    })
    @GetMapping(value = "/tickets", produces = "application/json")
    public List<Ticket> findAll() {
        log.debug("Find all tickets");

        return ticketService.findAll();
    }

    @ApiOperation(value = "Find all paid tickets", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of paid tickets"),
            @ApiResponse(code = 500, message = "Internal server error ")
    })
    @GetMapping(value = "/paid-tickets", produces = "application/json")
    public List<Ticket> findAllPaidTickets() {
        log.debug("Find all paid-tickets");
        return ticketService.findAllPaidTickets();
    }


    @ApiOperation(value = "Find paid tickets by date", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of paid tickets by date"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "finishDate", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/search-paid-tickets", produces = "application/json")
    public List<Ticket> searchPaidTicketByDate(@RequestParam("startDate") String startDate,
                                               @RequestParam("finishDate") String finishDate) {

        log.debug("Search paid tickets by date (startDate: {}, finishDate: {})", startDate, finishDate);

        LocalDateTime startDateLocal = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
        LocalDateTime finishDateLocal = LocalDateTime.of(2021, 1, 1, 1, 1, 1);

        try {
            startDateLocal = LocalDate.parse(startDate).atTime(LocalTime.now());
            finishDateLocal = LocalDate.parse(finishDate).atTime(LocalTime.now());

        } catch (Exception ex) {
            log.debug("Exception because off converting data, default values (startDate: {}, finishDate: {})",
                                                            startDateLocal, finishDateLocal);

            log.debug("Exception message: ({})", ex.getMessage());
        }

        return ticketService.searchPaidTicketByDate(startDateLocal, finishDateLocal);
    }


    @ApiOperation(value = "Find ticket by id", response = Ticket.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully found ticket by id"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/tickets/{id}", produces = "application/json")
    public Ticket findById(@PathVariable("id") String id) {
        log.debug("Find ticket by id: ({})", id);
        return ticketService.findById(id);
    }


    @ApiOperation(value = "Add new ticket", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created new ticket"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParam(name = "ticket", required = true, value = "Ticket object", paramType = "body", dataType = "Ticket")
    @PostMapping(value = "/tickets")
    public void add(@RequestBody Ticket ticket) {
        log.debug("Add ticket: ({})", ticket);
        ticketService.add(ticket);
    }


    @ApiOperation(value = "Pay ticket", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Ticket was updated"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParam(name = "paid-ticket", value = "Paid-ticket", required = true, paramType = "body", dataType = "Ticket")
    @PutMapping(value = "/tickets")
    public void payTicket(@RequestBody Ticket ticket) {
        log.debug("Pay ticket: ({})", ticket);

        ticketService.payTicket(ticket);
    }


    @ApiOperation(value = "Delete ticket by id", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted ticket"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/tickets/{id}")
    public void delete(@PathVariable("id") String id) {
        log.debug("Delete ticket by id: ({})", id);
        ticketService.delete(id);
    }


    @ApiOperation(value = "Update ticket", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Ticket was updated"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParam(name = "ticket", value = "Ticket object", required = true, paramType = "body", dataType = "Ticket")
    @PutMapping("/update-tickets/{ticketId}")
    public void update(@RequestBody Ticket ticket,
                       @PathVariable("ticketId") String id) {
        log.debug("Update ticket, (old ticket: {}, new id: {})", ticket, id);
        ticket.setId(id);
        ticketService.update(ticket);
    }


    @ApiOperation(value = "Find paid tickets by date and directions", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of paid tickets by date"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "finishDate", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "cityFrom", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "cityTo", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping("/search-tickets")
    public List<Ticket> searchTicket(@RequestParam("startDate") String startDate,
                                     @RequestParam("finishDate") String finishDate,
                                     @RequestParam("cityFrom") String cityFrom,
                                     @RequestParam("cityTo") String cityTo) {

        log.debug("Search tickets by date & directions " +
                "(startDate: {}, finishDate: {}, cityFrom: {}, cityTo: {})",
                startDate, finishDate, cityFrom, cityTo);

        LocalDateTime startDateLocal;
        LocalDateTime finishDateLocal;

        try {
            startDateLocal = LocalDate.parse(startDate).atTime(LocalTime.of(1,1));
            finishDateLocal = LocalDate.parse(finishDate).atTime(LocalTime.of(1,1));

        } catch (Exception ex) {
            log.debug("Exception (searchTicket: {})", ex.getMessage());

            startDateLocal = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
            finishDateLocal = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        }

        return ticketService.searchTicket(startDateLocal, finishDateLocal, cityFrom, cityTo);
    }
}
