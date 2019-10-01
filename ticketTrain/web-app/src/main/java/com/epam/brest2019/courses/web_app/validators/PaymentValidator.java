package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Payment;
//import com.epam.brest2019.courses.model.Ticket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//import java.time.LocalDate;

@Component
public class PaymentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Payment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

//        Payment payment = (Payment) target;
//        Ticket ticket = new Ticket();

        ValidationUtils.rejectIfEmpty(errors, "ticketId", "ticketId.empty");
        ValidationUtils.rejectIfEmpty(errors, "paymentDate", "paymentDate.empty");


//        if (payment.getPaymentDate().isAfter(ticket.getTicketDate())) {
//            errors.rejectValue("paymentDate", "paymentDateNotCorrect");
//        }
    }
}
