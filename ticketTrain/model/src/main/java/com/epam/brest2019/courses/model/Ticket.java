package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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
    @Min(0)
    private BigDecimal ticketCost;
    /**
     * date train
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate ticketDate;
    /**
     * Direction of train_from
     */
    @NotNull
    private Integer ticketDirectionFrom;
    /**
     * Direction of train_to
     */
    @NotNull
    private Integer ticketDirectionTo;

    /**
     * CityFrom for sql-query
     */
    private String cityFrom;

    /**
     * CityTo for sql-query
     */
    private String cityTo;

    /**
     * Count ticket
     */
    private Integer countTicket;

    /**
     * Count cost of ticket
     */
    private BigDecimal countTicketSum;

    /**
     * Constructor without parameters
     */

    /**
     * Constructor without parameters.
     */
    public Ticket(){

    }

    /**
     * Constructor with parameters.
     *
     * @param ticketDirectionFrom
     * @param ticketDirectionTo
     */
    public Ticket(Integer ticketDirectionFrom, Integer ticketDirectionTo){
        this.ticketDirectionFrom = ticketDirectionFrom;
        this.ticketDirectionTo = ticketDirectionTo;
    }

    /**
     * Get total number of train.
     * @return countTicket.
     */
    public Integer getCountTicket() {
        return countTicket;
    }

    /**
     * Set this countTicket.
     * @param countTicket countTicket
     */
    public void setCountTicket(Integer countTicket) {
        this.countTicket = countTicket;
    }

    /**
     * Get total amount of tickets.
     * @return countTicketSum.
     */
    public BigDecimal getCountTicketSum() {
        return countTicketSum;
    }

    /**
     * Set countTicketSum.
     * @param countTicketSum countTicketSum
     */
    public void setCountTicketSum(BigDecimal countTicketSum) {
        this.countTicketSum = countTicketSum;
    }

    /**
     * Get this ticketDate of train.
     * @return ticketDate of train.
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * Set cityFrom.
     * @param cityFrom city from
     */
    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    /**
     * Get cityTo
     * @return cityTo.
     */
    public String getCityTo() {
        return cityTo;
    }

     /**
     * Set cityTo.
     * @param cityTo city to
     */
    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
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
     * Get this ticketCost of ticket.
     * @return Cost of ticket.
     */
    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    /**
     * Set this ticketCost of ticket.
     * @param ticketCost of ticket.
     */
    public void setTicketCost(final BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    /**
     * Get this ticketDate of train.
     * @return ticketDate of train.
     */
    public LocalDate getTicketDate() {
        return ticketDate;
    }

    /**
     * Set this ticketDate of train.
     * @param ticketDate of train.
     */
    public void setTicketDate(final LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    /**
     * Get this direction of train.
     * @return directionFrom of train.
     */
    public Integer getTicketDirectionFrom() {
        return ticketDirectionFrom;
    }

    /**
     * Set this direction of train.
     * @param ticketDirectionFrom of train.
     */
    public void setTicketDirectionFrom(Integer ticketDirectionFrom) {
        this.ticketDirectionFrom = ticketDirectionFrom;
    }

    /**
     * Get this direction_to of train.
     * @return direction_to of train.
     */
    public Integer getTicketDirectionTo() {
        return ticketDirectionTo;
    }

    /**
     * Set this direction of train.
     * @param ticketDirectionTo of train.
     */
    public void setTicketDirectionTo(Integer ticketDirectionTo) {
        this.ticketDirectionTo = ticketDirectionTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getTicketId(), ticket.getTicketId()) &&
                Objects.equals(getTicketCost(), ticket.getTicketCost()) &&
                Objects.equals(getTicketDate(), ticket.getTicketDate()) &&
                Objects.equals(getTicketDirectionFrom(), ticket.getTicketDirectionFrom()) &&
                Objects.equals(getTicketDirectionTo(), ticket.getTicketDirectionTo()) &&
                Objects.equals(getCityFrom(), ticket.getCityFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketId(), getTicketCost(), getTicketDate(), getTicketDirectionFrom(), getTicketDirectionTo());
    }
}