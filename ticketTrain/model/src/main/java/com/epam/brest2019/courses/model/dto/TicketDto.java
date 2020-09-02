package com.epam.brest2019.courses.model.dto;

import com.epam.brest2019.courses.model.City;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Data
public class TicketDto {

    String id;
    BigDecimal ticketCost;
    ZonedDateTime ticketDate;
    City fromCity;
    City toCity;

}
