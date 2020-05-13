package com.epam.brest2019.courses.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


/**
 * POJO Ticket for model.
 */
@Document(collection = "ticket")
public class Ticket {

    /**
     * Ticket Id
     */
    @Id
    private String id;
    /**
     * Cost of ticket
     */
    private BigDecimal ticketCost;

    /**
     * date train
     */
    private ZonedDateTime ticketDate;

    /**
     * Direction of train_from
     */
    private City fromCity;

    /**
     * Direction of train_to
     */
    private City toCity;

    @Indexed
    private ZonedDateTime paymentDate;

    @Email
    private String email;

    /**
     * Constructor without parameters.
     */
    public Ticket(){

    }

    public Ticket(
        BigDecimal ticketCost,
        ZonedDateTime ticketDate,
        City fromCity,
        City toCity,
        ZonedDateTime paymentDate,
        @Email String email)
    {
        this.ticketCost = ticketCost;
        this.ticketDate = ticketDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.paymentDate = paymentDate;
        this.email = email;
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

    public ZonedDateTime getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(ZonedDateTime ticketDate) {
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

    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", ticketCost=" + ticketCost +
                ", ticketDate=" + ticketDate +
                ", fromCity=" + fromCity +
                ", toCity=" + toCity +
                ", paymentDate=" + paymentDate +
                ", email='" + email + '\'' +
                '}';
    }
}