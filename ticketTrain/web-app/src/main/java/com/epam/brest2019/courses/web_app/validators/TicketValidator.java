package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class TicketValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
