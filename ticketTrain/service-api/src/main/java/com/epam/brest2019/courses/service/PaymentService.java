package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    /**
     * Get all payment's.
     *
     * @return list of all payment's
     */
    List<Payment> findAll();

    /**
     * Get all payment's with specified payment id.
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
     * Find all directions
     * @return list of paid-tickets
     */
    List<Payment> findAllWitchDirection();

    /**
     * Find paid-tickets by date
     *
     * @param startDate
     * @param finishDate
     * @return list of paid-tickets
     */
    List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate);
}
