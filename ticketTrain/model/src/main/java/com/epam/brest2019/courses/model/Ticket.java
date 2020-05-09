package com.epam.brest2019.courses.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * POJO Ticket for model.
 */
@Document
public class Ticket {

    /**
     * Ticket Id
     */
    @Id
    private String id;
    /**
     * Cost of ticket
     */
    @Min(0)
    private BigDecimal ticketCost;

    /**
     * date train
     */
    private LocalDate ticketDate;

    /**
     * Direction of train_from
     */
    private City fromCity;

    /**
     * Direction of train_to
     */
    private City toCity;


    /**
     * Constructor without parameters.
     */
    public Ticket(){

    }

    public Ticket(@Min(0) BigDecimal ticketCost, LocalDate ticketDate, City fromCity, City toCity) {
        this.ticketCost = ticketCost;
        this.ticketDate = ticketDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
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
                "id='" + id + '\'' +
                ", ticketCost=" + ticketCost +
                ", ticketDate=" + ticketDate +
                ", fromCity=" + fromCity +
                ", toCity=" + toCity +
                '}';
    }
}