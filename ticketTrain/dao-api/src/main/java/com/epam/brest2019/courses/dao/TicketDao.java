package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDtoCost;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Ticket DAO Interface
 */
public interface TicketDao {

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
     * Find tickets by date & direction
     * @param startDate
     * @param finishDate
     * @param fromCity
     * @param toCity
     *
     * @return list of tickets
     */
    List<Ticket> searchTicket(LocalDateTime startDate, LocalDateTime finishDate,
                              String fromCity, String toCity);

    /**
     * Find paid-tickets by date
     * @param startDate
     * @param finishDate
     *
     * @return list of tickets
     */
    List<Ticket> searchPaidTicketByDate(LocalDateTime startDate, LocalDateTime finishDate);

    /**
     * Get total cost of paid-tickets
     */
    TicketDtoCost sumPaidTicketCost();

    /**
     * Get Ticket By Id
     * @param ticketId ticketId.
     * @return Ticket
     */
    Ticket findById(String ticketId);

    /**
     * Persist new ticket.
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
     * Update for paid-ticket
     *
     * @param ticket ticket
     */
    void update(Ticket ticket);

    /**
     * Delete ticket with specified id.
     *
     * @param ticketId ticket id
     */
    void delete(String ticketId);
}
