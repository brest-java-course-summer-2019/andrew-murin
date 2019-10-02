package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Ticket DAO Interface
 */
public interface TicketDao {
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
    Optional<Ticket> findById(Integer ticketId);

    /**
     * Find tickets by date & direction
     * @param startDate
     * @param finishDate
     * @param directionFrom
     * @param directionTo
     * @return
     */
    List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                              Integer directionFrom, Integer directionTo);

    /**
     * Find all directions and replace numbers of directions on city names
     * @return
     */
    List<Ticket> findAllWithDirection();

}
