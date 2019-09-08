package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath: rest-spring-test.xml"})
public class PaymentRestControllerTest {

    @Autowired
    private PaymentRestController paymentRestController;

    @Autowired
    private PaymentService paymentService;

    ObjectMapper objectMapper = new ObjectMapper();
}
