package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface PaymentDao {

    /**
     * Get all payment's.
     *
     * @return list of all payment's
     */
    @Select("SELECT payment_id, payment_date, ticket_id FROM payment ORDER BY 2")
    List<Payment> findAll();

    /**
     * Get all payment's with specified payment id.
     *
     * @param ticketId ticket id
     * @return list of payment's by ticket id
     */
    @Select("SELECT payment_id, payment_date, ticket_id FROM payment WHERE ticket_id = #{ticketId}")
    List<Payment> findByTicketId(@Param("ticketId") Integer ticketId);

    /**
     * Get payment with specified id.
     *
     * @param paymentId payment id
     * @return payment by id
     */
    @Select("SELECT payment_id, payment_date, ticket_id FROM payment WHERE payment_id = #{paymentId}")
    Payment findById(@Param("paymentId") Integer paymentId);

    /**
     * Persist new payment.
     *
     * @param payment payment
     * @return payment
     */
    @Insert("INSERT INTO payment(payment_date, ticket_id) VALUES (#{payment.paymentDate}, #{payment.ticketId})")
    void add(@Param("payment") Payment payment);

    /**
     * Update payment.
     *
     * @param payment payment
     */
    @Update("UPDATE payment SET payment_date = #{payment.paymentDate} WHERE payment_id = #{payment.paymentId}")
    void update(@Param("payment") Payment payment);

    /**
     * Delete payment with specified id.
     *
     * @param paymentId payment id
     */
    @Delete("DELETE FROM payment WHERE payment_id = #{paymentId}")
    void delete(@Param("paymentId") Integer paymentId);

    /**
     * Find all directions
     * @return
     */
    @Select("SELECT t.ticket_id, c.city_name AS cityFrom, s.city_name AS cityTo, COUNT(p.payment_date) AS ticketCount, SUM(t.ticket_cost) AS" +
            "  totalCost FROM payment p INNER JOIN ticket t ON p.ticket_id = t.ticket_id INNER JOIN city c ON c.city_id = t.ticket_direction_from" +
            "  INNER JOIN city s ON s.city_id = t.ticket_direction_to GROUP BY t.ticket_id")
    List<Payment> findAllWitchDirection();

    /**
     * Find all paid-tickets by date
     * @param startDate
     * @param finishDate
     * @return
     */
    @Select("SELECT p.payment_id, p.ticket_id, p.payment_date, c.city_name AS cityFrom, s.city_name AS cityTo, t.ticket_cost AS ticketCost FROM payment p" +
            "  INNER JOIN ticket t ON p.ticket_id = t.ticket_id INNER JOIN city c ON c.city_id = t.ticket_direction_from INNER JOIN city s ON s.city_id = t.ticket_direction_to" +
            "  WHERE p.payment_date BETWEEN #{startDate} AND #{finishDate}")
    List<Payment> searchByDate(@Param("startDate") LocalDate startDate, @Param("finishDate") LocalDate finishDate);
}
