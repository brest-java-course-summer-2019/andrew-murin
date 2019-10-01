package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

//import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentValidatorTest {

    private Payment payment;

    private PaymentValidator paymentValidator = new PaymentValidator();

    private BindingResult result;

    @BeforeEach
    void setup() {
        payment = Mockito.mock(Payment.class);
        result = new BeanPropertyBindingResult(payment, "payment");
    }

    @Test
    void shouldRejectEmptyPaymentTicketId() {
        Mockito.when(payment.getPaymentId()).thenReturn(null);

        paymentValidator.validate(payment, result);

        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyPaymentDate() {
        Mockito.when(payment.getPaymentDate()).thenReturn(null);

        paymentValidator.validate(payment, result);

        assertTrue(result.hasErrors());
    }

//    @Test
//    void shouldRejectDateAfterPayment() {
//        Mockito.when(payment.getPaymentDate()).thenReturn(LocalDate.MAX);
//
//        paymentValidator.validate(payment, result);
//
//        assertTrue(result.hasErrors());
//    }
}
