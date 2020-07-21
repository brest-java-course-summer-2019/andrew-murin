package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDao {

    /**
     * Get  payment's.
     *
     * @return list of  payment's
     */
    List<Payment> find();

    /**
     * Get  payment's with specified payment id.
     *
     * @param ticketId ticket id
     * @return list of payment's by ticket id
     */
    List<Payment> findByTicketId(Integer ticketId);

    /**
     * Get payment with specified id.
     *
     * @param paymentId payment id
     * @return payment by id
     */
    Payment findById(Integer paymentId);

    /**
     * Persist new payment.
     *
     * @param payment payment
     * @return payment
     */
    void add(Payment payment);

    /**
     * Update payment.
     *
     * @param payment payment
     */
    void update(Payment payment);

    /**
     * Delete payment with specified id.
     *
     * @param paymentId payment id
     */
    void delete(Payment paymentId);

    /**
     * Find  directions
     * @return
     */
    List<Payment> findWitchDirection();

    /**
     * Find  paid-tickets by date
     * @param startDate
     * @param finishDate
     * @return
     */
    List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate);
}
