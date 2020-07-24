package com.epam.brest2019.courses.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
public class TicketTest {

    //BREST
    private static final Integer CITY_FROM = 1;
    //MINSK
    private static final Integer CITY_TO = 2;

    private static final String PRICE = "30";

    private static final String FROM_BREST = "BREST";

    private static final String TO_MINSK = "MINSK";


    private Ticket ticket = new Ticket();

    private City city = new City();


    @Test
    public void getTicketId() {
        ticket.setTicketId(5);
        assertEquals(ticket.getTicketId().intValue(),5);
    }

    @Test
    public void getTicketCost() {
        ticket.setTicketCost(new BigDecimal(PRICE));
        assertTrue(ticket.getTicketCost().compareTo(new BigDecimal(PRICE)) == 0);
    }

    @Test
    public void getLocalDate() {
        LocalDate localDate = LocalDate.of(2019,07,15);
        ticket.setTicketDate(localDate);
        assertEquals(localDate, ticket.getTicketDate());
    }

    @Test
    public void getCityFrom() {
        city = createFixture();

        ticket.setFromCity(city);

        assertEquals(ticket.getFromCity().getCityId(), CITY_FROM);
    }

    @Test
    public void getCityTo() {
        city = createFixture();

        ticket.setToCity(city);

        assertEquals(ticket.getToCity().getCityName(), TO_MINSK);
    }

    private City createFixture() {
        City city = new City();

        city.setCityId(CITY_FROM);
        city.setCityName(TO_MINSK);

        return city;
    }

}
