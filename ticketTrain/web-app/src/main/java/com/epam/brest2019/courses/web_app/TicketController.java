package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.web_app.validators.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketValidator ticketValidator;

    @GetMapping("/tickets-all")
    public final String tickets(Model model) {
        LOGGER.debug("Find all tickets ({})", model);
        model.addAttribute("tickets-all", ticketService.findAll());
        return "tickets-all";
    }

    @GetMapping("/ticket/{id}")
    public final String gotoPageForEditTicket(@PathVariable Integer id, Model model) {
        LOGGER.debug("Go to edit ticket page({},{})", id, model);
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @PostMapping("/tickets-all/{id}}")
    public final String updateTicket(@Valid Ticket ticket, BindingResult bindingResult){
        LOGGER.debug("Update ticket ({},{})", ticket, bindingResult);
        ticketValidator.validate(ticket,bindingResult);
        if(bindingResult.hasErrors()) {
            return "ticket";
        } else {
            this.ticketService.update(ticket);
            return "redirect:/tickets-all";
        }
    }

    @GetMapping("/ticket")
    public final String gotoAddTicketPage(Model model){
        LOGGER.debug("goto add ticket page ({})", model);
        Ticket ticket = new Ticket();
        model.addAttribute("isNew", true);
        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @PostMapping
    public final String addTicket(@Valid Ticket ticket, BindingResult bindingResult) {
        LOGGER.debug("add ticket ({}, {})", ticket, bindingResult);
        ticketValidator.validate(ticket, bindingResult);
        if(bindingResult.hasErrors()) {
            return "ticket";
        } else {
            this.ticketService.add(ticket);
            return "redirect:/tickets-all";
        }
    }

    public final String deleteTicketById(@PathVariable Integer id, Model model){
        LOGGER.debug("delete ticket by id ({}, {})", id, model);
        ticketService.delete(id);
        return "redirect/tickets-all";
    }

}
