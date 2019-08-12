package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Ticket;

import java.util.List;

/**
 * Ticket Service Interface
 */
public interface TicketService {
    /**
     * Add new ticket.
     *
     * @param tickets new ticket
     * @return new ticket object.
     */
    void add(Ticket... tickets);

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
}

