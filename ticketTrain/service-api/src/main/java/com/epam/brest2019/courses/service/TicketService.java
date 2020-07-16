package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;

import java.time.LocalDateTime;
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
    Ticket add(Ticket ticket);

    /**
     * Update ticket
     *
     * @param ticket ticket
     */
    void payTicket(Ticket ticket);

    /**
     * Delete ticket with specified id.
     *
     * @param ticketId ticket id
     */
    void delete(String ticketId);

    /**
     * Get tickets.
     *
     * @return tickets list.
     */
    List<Ticket> findAll();

    /**
     * Get paid-tickets.
     *
     * @return tickets list.
     */
    List<Ticket> findAllPaidTickets();

    /**
     * Get Ticket By Id
     * @param ticketId ticketId.
     * @return Ticket
     */
    Ticket findById(String ticketId);

    /**
     * Find direction by date and directions
     * @param startDate
     * @param finishDate
     * @param cityFrom
     * @param cityTo
     *
     * @return list of tickets
     */
    List<Ticket> searchTicket(LocalDateTime startDate, LocalDateTime finishDate,
                              String cityFrom, String cityTo);


    /**
     * Find tickets by date
     * @param startDate
     * @param finishDate
     *
     * @return list of paid-tickets
     */
    List<Ticket> searchPaidTicketByDate(LocalDateTime startDate, LocalDateTime finishDate);

    /**
     * Update ticket
     *
     * @param ticket ticket.
     */
    void update(Ticket ticket);


    /**
     * Count total cost of paid-tickets
     */
    TicketDto sumPaidTicketCost();

}

