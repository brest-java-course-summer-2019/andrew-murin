package com.epam.brest2019.courses.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    /**
     * Ticket Id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    @Fetch(FetchMode.JOIN)
    private Ticket ticketId;

    /**
     * Summing all costs by different directions
     */
    @Transient
    private BigDecimal ticketCost = new BigDecimal(2);

    /**
     * Counting all tickets by different directions
     */
    @Transient
    private Long ticketCount = 5L;

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
     * Use for jpql-query
     *
     * @param ticketId
     * @param ticketCount
     * @param ticketCost
     */
    public Payment(Ticket ticketId, Long ticketCount, BigDecimal ticketCost) {
        this.ticketId = ticketId;
        this.ticketCount = ticketCount;
        this.ticketCost = ticketCost;
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
    public Long getTicketCount() {
        return ticketCount;
    }

    /**
     * Set ticketCount
     *
     * @param ticketCount
     */
    public void setTicketCount(Long ticketCount) {
        this.ticketCount = ticketCount;
    }
}
