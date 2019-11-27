package com.epam.brest2019.courses.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * POJO Payment for model
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    /**
     * Payment Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer paymentId;

    /**
     * Local Date
     */
    @Column(name = "payment_date")
    @Type(type = "org.hibernate.type.LocalDateType")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    /**
     * Ticket Id
     */
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticketId;

    /**
     * Summing all costs by different directions
     */
    @Transient
    private BigDecimal ticketCost;

    /**
     * Counting all tickets by different directions
     */
    @Transient
    private Integer ticketCount;

    /**
     *For get total amount by all directions
     */
    @Transient
    private BigDecimal totalCost;

    /**
     * Constructor without parameters
     */
    public Payment() {
    }

    /**
     * Constructor with parameters
     *
     * @param localDate
     * @param ticketId
     */
    public Payment(LocalDate localDate, Ticket ticketId) {
        this.paymentDate = localDate;
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
     * @return paymentDate LocalDate
     */

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the dates identifier
     *
     * @param paymentDate LocalDate
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Returns <code>Integer</code> representation of this date.
     *
     * @return ticketID Ticket Id
     */
    public Ticket getTicketId() {
        return ticketId;
    }

    /**
     * Sets the ticketId identifier
     *
     * @param ticketId Ticket Id
     */
    public void setTicketId(Ticket ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Get totalCost
     *
     * @return totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * Set totalCost
     *
     * @param totalCost
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Get ticketCost
     *
     * @return ticketCost
     */
    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    /**
     * Set ticketCost
     *
     * @param ticketCost
     */
    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    /**
     * Get ticketCount
     *
     * @return
     */
    public Integer getTicketCount() {
        return ticketCount;
    }

    /**
     * Set ticketCount
     *
     * @param ticketCount
     */
    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

}
