package com.epam.brest2019.courses.model.dto;


import java.math.BigDecimal;

public class TicketDto {

    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
