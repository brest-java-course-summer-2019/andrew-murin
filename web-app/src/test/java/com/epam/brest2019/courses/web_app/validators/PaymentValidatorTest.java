package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

public class PaymentValidatorTest {

    private Payment payment;

    private PaymentValidator paymentValidator = new PaymentValidator();

    private BindingResult result;

    @BeforeEach
    void setup() {
        payment = Mockito.mock(Payment.class);
        result = new BeanPropertyBindingResult(payment, "payment");
    }


}
