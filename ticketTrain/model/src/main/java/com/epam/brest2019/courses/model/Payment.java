package com.epam.brest2019.courses.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * POJO Payment for model
 */
@Entity
@Table(name = "payment")
public class Payment {

    /**
     * Payment Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    /**
     * Local Date
     */
    @Column(name = "payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    /**
     * Ticket Id
     */
    @Column(name = "ticket_id")
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private List<Ticket> ticketId;

    /**
     * City from for sql-query
     */
    @Transient
    private String cityFrom;

    /**
     * City To for sql-query
     */
    @Transient
    private String cityTo;

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
     * @param paymentDate
     * @param ticketId
     */
    public Payment(LocalDate paymentDate, List<Ticket> ticketId) {
        this.paymentDate = paymentDate;
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
    public List<Ticket> getTicketId() {
        return ticketId;
    }

    /**
     * Sets the ticketId identifier
     *
     * @param ticketId Ticket Id
     */
    public void setTicketId(List<Ticket> ticketId) {
        this.ticketId = ticketId;
    }


    /**
     * Get cityFrom
     *
     * @return cityFrom city from
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * Set cityFrom
     *
     * @param cityFrom city from
     */
    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    /**
     * Get cityTo
     *
     * @return cityTo city to
     */
    public String getCityTo() {
        return cityTo;
    }

    /**
     *Set cityTo
     *
     * @param cityTo
     */
    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
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


    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", PaymentDate=" + paymentDate +
                ", ticketId=" + ticketId +
                '}';
    }
}
