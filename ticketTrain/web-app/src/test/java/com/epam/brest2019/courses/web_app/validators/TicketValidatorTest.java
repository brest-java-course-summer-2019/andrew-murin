package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketValidatorTest {

    private Ticket ticket;
    private Ticket tickets;

    private TicketValidator ticketValidator = new TicketValidator();

    private BindingResult result;

    @BeforeEach
    void setUp() {
        ticket = Mockito.mock(Ticket.class);
        result = new BeanPropertyBindingResult(ticket, "ticket");
    }

    //Ticket can't be earlier that now
    @Test
    void ShouldRejectDateAfterNow() {
        City city = new City();
        city.setCityId(1);
        city.setCityName("BREST");

        Mockito.when(ticket.getTicketCost()).thenReturn(new BigDecimal(10));
        Mockito.when(ticket.getTicketDate()).thenReturn(LocalDate.MIN);
        Mockito.when(ticket.getFromCity()).thenReturn(city);

        city.setCityId(2);
        city.setCityName("MINSK");

        Mockito.when(ticket.getToCity()).thenReturn(city);
        Mockito.when(ticket.getTicketId()).thenReturn(1);


        ticketValidator.validate(ticket, result);

        assertTrue(result.hasErrors());
    }


    @Test
    void shouldRejectCostSmallZero() {
        City city = new City();
        city.setCityId(1);
        city.setCityName("BREST");

        Mockito.when(ticket.getTicketCost()).thenReturn(new BigDecimal(-6));
        Mockito.when(ticket.getTicketDate()).thenReturn(LocalDate.MAX);
        Mockito.when(ticket.getFromCity()).thenReturn(city);

        city.setCityId(2);
        city.setCityName("MINSK");

        Mockito.when(ticket.getToCity()).thenReturn(city);
        Mockito.when(ticket.getTicketId()).thenReturn(1);



        ticketValidator.validate(ticket, result);

        assertTrue(result.hasErrors());
    }


}