package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.web_app.validators.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Ticket controller
 */
@Controller
public class TicketController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);


    @Autowired
    private TicketService ticketService;

    @Autowired
    TicketValidator ticketValidator;

    /**
     * Find tickets by date & directions
     * @param startDate
     * @param finishDate
     * @param directionFrom
     * @param directionTo
     * @param model
     * @return tickets
     */
    @PostMapping("/search-tickets")
    public final String searchTickets(@ModelAttribute("startDate") String startDate,
                                      @ModelAttribute("finishDate") String finishDate,
                                      @ModelAttribute("directionFrom") int directionFrom,
                                      @ModelAttribute("directionTo") int directionTo,
                                      Model model) {
        LOGGER.debug("Search tickets by date({} - {})", startDate, finishDate);

        LocalDate startDateView;
        LocalDate finishDateView;

        try{
            startDateView = LocalDate.parse(startDate);
            finishDateView = LocalDate.parse(finishDate);

        } catch (Exception ex) {
            startDateView = LocalDate.now();
            finishDateView = LocalDate.of(2019,12,31);
        }

        List<Ticket> tickets = ticketService.searchTicket(startDateView, finishDateView, directionFrom, directionTo);

        model.addAttribute("tickets", tickets);

        return "tickets";
    }


    /**
     * Goto search-tickets page.
     *
     * @return search-tickets
     */
    @GetMapping("/search-tickets")
    public final String gotoSearchTickets(Model model) {
        LOGGER.debug("Search tickets");

        List<Ticket> tickets = ticketService.findAllWithDirection();
        model.addAttribute("tickets", tickets);

        return "search-tickets";
    }


    /**
     * Goto tickets page.
     *
     * @param model model
     * @return tickets
     */

    @GetMapping("/tickets")
    public final String findAllWithDirection(Model model) {
        LOGGER.debug("Find all tickets");

        List<Ticket> tickets = ticketService.findAllWithDirection();

        model.addAttribute("tickets", tickets);

        return "tickets";
    }

    /**
     * Goto edit ticket page.
     *
     * @param id
     * @param model
     * @return ticket/id.
     */
    @GetMapping("/ticket/{id}")
    public final String gotoEditTicketPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditTicketPage({})", id);
        Ticket ticket = ticketService.findById(id);

        model.addAttribute("ticket", ticket);

        return "ticket";
    }

    /**
     *Update department into persistence storage.
     *
     * @param ticket
     * @return tickets.
     */
    @PostMapping("/ticket/{id}")
    public final String updateTicket(@ModelAttribute("ticketForm") @Valid Ticket ticket, BindingResult result) {
        LOGGER.debug("Update ticket({}, {})",ticket, result);


        ticketValidator.validate(ticket, result);

        if (result.hasErrors()) {
            return "ticket/" + ticket.getTicketId();

        } else {
            this.ticketService.update(ticket);
            return "redirect:/tickets";
        }

    }

    /**
     * Goto add ticket page
     *
     * @param model
     * @return ticket
     */
    @GetMapping("/ticket")
    public final String gotoTicketAddPage(Model model) {
        LOGGER.debug("Go to add ticket page({})", model);

        List<Ticket> tickets = ticketService.findAll();

        Ticket ticket = new Ticket();

        model.addAttribute("isNew", true);
        model.addAttribute("ticket", ticket);
        model.addAttribute("tickets", tickets);

        return "ticket";
    }

    /**
     * Persist new ticket into persistence storage.
     *
     * @param ticket new department with filled data.
     * @return tickets
     */
    @PostMapping("/ticket")
    public final String addTicket(@Valid Ticket ticket, BindingResult result) {
        LOGGER.debug("Add Ticket({}, {})", ticket, result);

        ticketValidator.validate(ticket, result);

        if (result.hasErrors()) {
            return "ticket/" + ticket.getTicketId();
        } else {
            this.ticketService.add(ticket);
            return "redirect:/tickets";
        }
    }

    /**
     * Delete ticket
     *
     * @param ticketId, model
     * @return tickets.
     */
    @GetMapping("/ticket/{id}/delete")
    public final String deleteTicketById(@PathVariable("id") Integer ticketId) {
        LOGGER.debug("Delete ticket by id({})", ticketId);
        ticketService.delete(ticketId);

        return "redirect:/tickets";
    }

}
