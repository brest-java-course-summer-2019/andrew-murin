package com.epam.brest2019.courses.rest_app.controllers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;
import com.epam.brest2019.courses.service.TicketService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
@Api(value = "Ticket resource")
public class TicketRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestController.class);

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
        LOGGER.debug("Find all cost of paid tickets");
         return ticketService.sumPaidTicketCost();
    }

    @ApiOperation(value = "Find all tickets", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of tickets"),
            @ApiResponse(code = 500, message = "Internal server error ")
    })
    @GetMapping(value = "/tickets", produces = "application/json")
    public List<Ticket> findAll() {
        LOGGER.debug("Find all tickets");

        return ticketService.findAll();
    }

    @ApiOperation(value = "Find all paid tickets", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of paid tickets"),
            @ApiResponse(code = 500, message = "Internal server error ")
    })
    @GetMapping(value = "/paid-tickets", produces = "application/json")
    public List<Ticket> findAllPaidTickets() {
        LOGGER.debug("Find all tickets");
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

    @ApiOperation(value = "Find ticket by id", response = Ticket.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully found ticket by id"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping(value = "/tickets/{id}", produces = "application/json")
    public Ticket findById(@PathVariable("id") String id) {
        LOGGER.debug("Find ticket by id ({})", id);
        return ticketService.findById(id);
    }

//
//    @ApiOperation(value = "Add new ticket", response = void.class)
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "Created new ticket"),
//            @ApiResponse(code = 500, message = "Internal server error")
//    })
//    @ApiImplicitParam(name = "ticket", required = true, value = "Ticket object", paramType = "body", dataType = "Ticket")
//    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/tickets")
    public void add(@RequestBody Ticket ticket) {
        LOGGER.debug("Add ticket ({})", ticket);
        ticketService.add(ticket);
    }


    @ApiOperation(value = "Pay ticket", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Ticket was updated"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paymentDate", value = "Date of purchase ticket", required = true, paramType = "header", dataType = "string"),
//            @ApiImplicitParam(name = "email", value = "Email of bayer", required = true, paramType = "header", dataType = "string")
//    })
    @PutMapping(value = "/tickets")
    public void payTicket(@RequestBody Ticket ticket) {

//        LOGGER.debug("Pay ticket. ticketId {}, paymentDate {}, email {})", ticketId, paymentDate, email);

//        ZonedDateTime paymentDateLocal =
//                LocalDate.parse(paymentDate).atStartOfDay(ZoneId.systemDefault());
//
//        Ticket ticket = ticketService.findById(ticketId);
//
//        ticket.setPaymentDate(paymentDateLocal);
//        ticket.setEmail(email);

        ticketService.payTicket(ticket);
    }


    @ApiOperation(value = "Delete ticket by id", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted ticket"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/tickets/{id}")
    public void delete(@PathVariable("id") String ticketId) {
        LOGGER.debug("Delete ticket ({})", ticketId);
        ticketService.delete(ticketId);
    }


    @ApiOperation(value = "Update ticket", response = void.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Ticket was updated"),
            @ApiResponse(code = 404, message = "Ticket not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParam(name = "ticket", value = "Ticket object", required = true, paramType = "body", dataType = "Ticket")
    @PutMapping("/update-tickets/{ticketId}")
    public void update(@RequestBody Ticket ticket, @PathVariable("ticketId") String ticketId) {
        LOGGER.debug("Update ticket ({})", ticket);
        ticket.setId(ticketId);
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

        LOGGER.debug("Search tickets by date & directions");

        LocalDateTime startDateLocal = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
        LocalDateTime finishDateLocal = LocalDateTime.of(2020, 1, 1, 1, 1, 1);

        try {
            startDateLocal = LocalDate.parse(startDate).atTime(LocalTime.of(1,1));
            finishDateLocal = LocalDate.parse(finishDate).atTime(LocalTime.of(1,1));

        } catch (Exception ex) {
            LOGGER.debug("Exception(searchTicket) {}", ex.getMessage());
        }

        return ticketService.searchTicket(startDateLocal, finishDateLocal, cityFrom, cityTo);
    }
}
