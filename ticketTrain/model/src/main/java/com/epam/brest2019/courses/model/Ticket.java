package com.epam.brest2019.courses.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Type;
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
    @Column(name ="id")
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

    @Type(type = "org.hibernate.type.LocalDateType")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "ticket_date")
    private LocalDate ticketDate;

    /**
     * Direction of train_from
     */
    @ManyToOne
    @JoinColumn(name = "from_city", foreignKey = @ForeignKey(name = "fk_from_city"))
    private City fromCity;

    /**
     * Direction of train_to
     */
    @ManyToOne
    @JoinColumn(name = "to_city", foreignKey = @ForeignKey(name = "fk_to_city"))
    private City toCity;


    /**
     * Constructor without parameters.
     */
    public Ticket(){

    }

    public Ticket(Integer ticketId, @Min(0) @NotNull BigDecimal ticketCost, LocalDate ticketDate, @NotNull City fromCity, @NotNull City toCity) {
        this.ticketId = ticketId;
        this.ticketCost = ticketCost;
        this.ticketDate = ticketDate;
        this.fromCity = fromCity;
        this.toCity = toCity;

    }

    /**
     * Constructor with parameters.
     *
     * @param fromCity
     * @param toCity
     */
    public Ticket(City fromCity, City toCity){
        this.fromCity = fromCity;
        this.toCity = toCity;
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

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) &&
                Objects.equals(ticketCost, ticket.ticketCost) &&
                Objects.equals(ticketDate, ticket.ticketDate) &&
                Objects.equals(fromCity, ticket.fromCity) &&
                Objects.equals(toCity, ticket.toCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, ticketCost, ticketDate, fromCity, toCity);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", ticketCost=" + ticketCost +
                ", ticketDate=" + ticketDate +
                ", fromCity=" + fromCity +
                ", toCity=" + toCity +
                '}';
    }
}