package com.epam.brest2019.courses.model;

import java.time.LocalDate;

/**
 * POJO Payment for model
 */
public class Payment {
    /**
     * Payment Id
     */
    private Integer paymentId;

    /**
     * Local Date
     */
    private LocalDate localDate;

    /**
     * Ticket Id
     */
    private Integer ticketId;

    public Payment() {
    }

    public Payment(LocalDate localDate, Integer ticketId) {
        this.localDate = localDate;
        this.ticketId = ticketId;
    }

    /**
     * Returns <code>Integer</code> representation of this paymentId.
     *
     * @return paymentId Payment Id
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment's identifier
     *
     * @param paymentId Payment Id
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Returns <code>Integer</code> representation of this date.
     *
     * @return localDate LocalDate
     */

    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Sets the dates identifier
     *
     * @param localDate LocalDate
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Returns <code>Integer</code> representation of this date.
     *
     * @return ticketID Ticket Id
     * @return ticketID Ticket Id
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * Sets the ticketId identifier
     *
     * @param ticketId Ticket Id
     */
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", LocalDate=" + localDate +
                ", ticketId=" + ticketId +
                '}';
    }
}
