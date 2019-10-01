package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;


@Component
public class TicketValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ticket ticket = (Ticket) target;

//TODO add handler for serverError

        if (ticket.getTicketDirectionFrom().equals(ticket.getTicketDirectionTo())){
            errors.rejectValue("ticketDirectionFrom", "ticketDirection");
        }

        if (ticket.getTicketDate().isAfter(LocalDate.now())) {
            errors.rejectValue("ticketDate", "ticketDate.BeforeNow");
        }

        if (ticket.getTicketCost().intValue() <= 0) {
            errors.rejectValue("ticketCost","smallerNull");
        }

        if (ticket.getTicketDate().isAfter(LocalDate.now())) {
            errors.rejectValue("ticketDate","ticketDate.BeforeNow");
        }
    }
}
