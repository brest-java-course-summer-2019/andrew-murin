package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Payment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Payment.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
