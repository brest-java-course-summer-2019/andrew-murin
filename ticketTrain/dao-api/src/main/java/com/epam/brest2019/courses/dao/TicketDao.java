package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;

import java.time.LocalDate;
import java.util.List;

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
    void delete(Ticket ticketId);

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
     * Find tickets by date & direction
     * @param startDate
     * @param finishDate
     * @param fromCity
     * @param toCity
     * @return
     */
    List<Ticket> searchTicket(LocalDate startDate, LocalDate finishDate,
                              Integer fromCity, Integer toCity);

    /**
     * Find all directions and replace numbers of directions on city names
     * @return
     */
    List<Ticket> findAllWithDirection();

}
