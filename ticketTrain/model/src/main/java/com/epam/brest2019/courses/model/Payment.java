package com.epam.brest2019.courses.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDate;

/**
 * POJO Payment for model
 */
@Document
public class Payment {

    /**
     * Payment Id
     */
    @Id
    private String id;

    /**
     * Local Date
     */
    private LocalDate paymentDate;

    @Email
    private String email;

    /**
     * Ticket Id
     */
    private Ticket ticketId;

    /**
     * Constructor without parameters
     */
    public Payment() {
    }

    public Payment(LocalDate paymentDate, @Email String email, Ticket ticketId) {
        this.paymentDate = paymentDate;
        this.email = email;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ticket getTicketId() {
        return ticketId;
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", paymentDate=" + paymentDate +
                ", email='" + email + '\'' +
                ", ticketId=" + ticketId +
                '}';
    }
}
