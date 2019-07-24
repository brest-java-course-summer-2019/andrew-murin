package com.epam.brest2019.courses.model;

import java.math.BigDecimal;

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
    private String dataTime;
    /**
     * Direction of train
     */
    private String ticketDirection;


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
    public String getDataTime() {
        return dataTime;
    }

    /**
     * Set this date of train.
     * @param dataTime of train.
     */
    public void setDataTime(final String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * Get this direction of train.
     * @return direction of train.
     */
    public String getDirection() {
        return ticketDirection;
    }

    /**
     * Set this direction of train.
     * @param ticketDirection of train.
     */
    public void setDirection(final String ticketDirection) {
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
                ", dataTime='" + dataTime + '\'' +
                ", direction='" + ticketDirection + '\'' +
                '}';
    }
}