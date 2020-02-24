package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.consumer.PaymentRestConsumer;
import com.epam.brest2019.courses.consumer.TicketRestConsumer;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableIntegration
@IntegrationComponentScan(basePackages = "com.epam.brest2019.courses.*")
@SpringBootApplication
@EnableJms
public class Application {

    @Value("${rest.url}")
    private String restUrl;

    @Value("${rest.payments}")
    private String restPayments;

    @Value("${rest.tickets}")
    private String restTickets;

    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PaymentService paymentService() {
        PaymentService paymentService = new PaymentRestConsumer(restUrl + restPayments, restTemplate());
        return paymentService;
    }

    @Bean
    public TicketService ticketService() {
        TicketService ticketService = new TicketRestConsumer(restUrl + restTickets, restTemplate());
        return ticketService;
    }


    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("content-type");
        converter.setTypeIdMappings(Collections.singletonMap("payment", Payment.class));
        return converter;
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConveter = new MappingJackson2HttpMessageConverter();
        jsonMessageConveter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConveter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
