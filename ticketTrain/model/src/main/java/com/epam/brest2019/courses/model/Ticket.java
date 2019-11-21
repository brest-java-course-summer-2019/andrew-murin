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
    @Column(name ="id")
    private Integer ticketId;
    /**
     * Cost of ticket
     */
    @Min(0)
    @NotNull
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
    @ManyToOne
    @JoinColumn(name = "from_city", foreignKey = @ForeignKey(name = "fk_from_city"))
    private City fromCity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "to_city", foreignKey = @ForeignKey(name = "fk_to_city"))
    private City toCity;

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

    /**
     * Constructor without parameters.
     */
    public Ticket(){

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