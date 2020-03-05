package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Ticket DAO Interface
 */
@Mapper
@Repository
public interface TicketDao {
    /**
     * Persist new ticket.
     *
     * @param ticket new ticket
     * @return new ticket object.
     */
    @Insert("INSERT INTO ticket (ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date)" +
            "  VALUES (#{ticket.ticketDirectionFrom}, #{ticket.ticketDirectionTo}, #{ticket.ticketCost}, #{ticket.ticketDate})")
    void add(@Param("ticket")Ticket ticket);

    /**
     * Update ticket
     *
     * @param ticket ticket
     */
    @Update("UPDATE ticket SET ticket_direction_from = #{ticket.ticketDirectionFrom}, ticket_direction_to = #{ticket.ticketDirectionTo}," +
            " ticket_cost = #{ticket.ticketCost}, ticket_date = #{ticket.ticketDate} where ticket_id = #{ticket.ticketId}")
    void update(@Param("ticket") Ticket ticket);

    /**
     * Delete ticket with specified id.
     *
     * @param ticketId ticket id
     */
    @Delete("DELETE FROM ticket WHERE ticket_id = #{ticketId}")
    void delete(Integer ticketId);

    /**
     * Get tickets.
     *
     * @return tickets list.
     */
    @Select("SELECT ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date FROM ticket ORDER BY 4")
    List<Ticket> findAll();

    /**
     * Get Ticket By Id
     * @param ticketId ticketId.
     * @return Ticket
     */
    @Select("SELECT ticket_id, ticket_direction_from, ticket_direction_to, ticket_cost, ticket_date FROM ticket" +
            " WHERE ticket_id = #{ticketId}")
    Ticket findById(@Param("ticketId") Integer ticketId);

    /**
     * Find tickets by date & direction
     * @param startDate
     * @param finishDate
     * @param directionFrom
     * @param directionTo
     * @return
     */
    @Select("SELECT t.ticket_id, t.ticket_direction_from, t.ticket_direction_to, t.ticket_cost, t.ticket_date, c.city_name AS" +
            " cityFrom, s.city_name AS cityTo FROM ticket t INNER JOIN city c ON t.ticket_direction_from = c.city_id INNER JOIN city s" +
            " ON t.ticket_direction_to = s.city_id WHERE t.ticket_date BETWEEN #{startDate} AND #{finishDate}" +
            " AND t.ticket_direction_from = #{directionFrom} AND t.ticket_direction_to = #{directionTo}")
    List<Ticket> searchTicket(@Param("startDate") LocalDate startDate, @Param("finishDate") LocalDate finishDate,
                              @Param("directionFrom") Integer directionFrom, @Param("directionTo") Integer directionTo);

    /**
     * Find all directions and replace numbers of directions on city names
     * @return
     */
    @Select("SELECT ticket_id, c.city_name AS cityFrom, s.city_name as cityTo, ticket_cost, ticket_date FROM ticket" +
            "  INNER JOIN city c ON ticket_direction_from = c.city_id INNER JOIN city s ON ticket_direction_to = s.city_id")
    List<Ticket> findAllWithDirection();

}
