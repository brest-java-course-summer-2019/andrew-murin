package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO Ticket for model.
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    /**
     * Ticket Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Integer ticketId;
    /**
     * Cost of ticket
     */
    @Min(0)
    @Column(name = "ticket_cost")
    private BigDecimal ticketCost;
    /**
     * date train
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "ticket_date")
    private LocalDate ticketDate;
    /**
     * Direction of train_from
     */
    @NotNull
    @Column(name = "ticket_direction_from")

    private City ticketDirectionFrom;
    /**
     * Direction of train_to
     */
    @NotNull
    @Column(name = "ticket_direction_to")

    private City ticketDirectionTo;

    /**
     * CityFrom for sql-query
     */
    @Transient
    private String cityFrom;

    /**
     * CityTo for sql-query
     */
    @Transient
    private String cityTo;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
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
    public Ticket(City ticketDirectionFrom, City ticketDirectionTo){
        this.ticketDirectionFrom = ticketDirectionFrom;
        this.ticketDirectionTo = ticketDirectionTo;
    }

     /** Get this ticketDate of train.
     *
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
    public City getTicketDirectionFrom() {
        return ticketDirectionFrom;
    }

    /**
     * Set this direction of train.
     * @param ticketDirectionFrom of train.
     */
    public void setTicketDirectionFrom(City ticketDirectionFrom) {
        this.ticketDirectionFrom = ticketDirectionFrom;
    }

    /**
     * Get this direction_to of train.
     * @return direction_to of train.
     */
    public City getTicketDirectionTo() {
        return ticketDirectionTo;
    }

    /**
     * Set this direction of train.
     * @param ticketDirectionTo of train.
     */
    public void setTicketDirectionTo(City ticketDirectionTo) {
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