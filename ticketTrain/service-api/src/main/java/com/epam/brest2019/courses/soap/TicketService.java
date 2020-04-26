package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Ticket;

import java.time.LocalDate;
import java.util.List;


/**
 * Ticket Service Interface
 */
public interface TicketService {
    /**
     * Add new ticket.
     *
     * @param ticket new ticket
     * @return new ticket object.
     */
    void add(Ticket ticket);

    /**
     * Update ticket
     *
     * @param ticket ticket
     */
    void update(Ticket ticket);

    /**
     * Delete ticket with specified id.
     *
     * @param ticketId ticket id
     */
    void delete(Integer ticketId);

    /**
     * Get tickets.
     *
     * @return tickets list.
     */
    List<Ticket> findAll();

    /**
     * Get Ticket By Id
     * @param ticketId ticketId.
     * @return Ticket
     */
    Ticket findById(Integer ticketId);

    /**
     * Find direction by date and directions
     * @param startDate
     * @param finishDate
     * @param directionFrom
     * @param directionTo
     * @return
     */
    List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                              Integer directionFrom, Integer directionTo);

    /**
     * Find all tickets and replace numbers on city names
     * @return
     */
    List<Ticket> findAllWithDirection();

}

