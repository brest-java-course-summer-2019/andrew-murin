package com.epam.brest2019.courses.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * POJO Ticket for model.
 */
public class Ticket {

    /**
     * Ticket Id
     */
    private Integer ticketId;
    /**
     * Cost of ticket
     */
    private BigDecimal cost;
    /**
     * date train
     */
    private LocalDate localDate;
    /**
     * Direction of train
     */
    private String ticketDirection;

    public Ticket(){

    }

    public Ticket(String ticketDirection){
        this.ticketDirection = ticketDirection;
    }

    /**
     * Get this ticket id.
     * @return Ticket Id.
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * Set this ticket id.
     * @param ticketId Ticket Id .
     */
    public void setTicketId(final Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Get this cost of ticket.
     * @return Cost of ticket.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Set this cost of ticket.
     * @param cost of ticket.
     */
    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Get this date of train.
     * @return date of train.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Set this date of train.
     * @param localDate of train.
     */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Get this direction of train.
     * @return direction of train.
     */
    public String getTicketDirection() {
        return ticketDirection;
    }

    /**
     * Set this direction of train.
     * @param ticketDirection of train.
     */
    public void setTicketDirection(final String ticketDirection) {
        this.ticketDirection = ticketDirection;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", cost=" + cost +
                ", localDate='" + localDate + '\'' +
                ", direction='" + ticketDirection + '\'' +
                '}';
    }
}