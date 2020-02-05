package com.epam.brest2019.courses.consumer.config;

import com.epam.brest2019.courses.consumer.PaymentRestConsumer;
import com.epam.brest2019.courses.consumer.TicketRestConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ConsumerConfiguration extends WebMvcConfigurerAdapter {

    @Value("${rest.url}")
    private String restUrl;

    @Value("${rest.payments}")
    private String restPayments;

    @Value("${rest.tickets}")
    private String restTickets;

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public PaymentRestConsumer paymentService() {
        PaymentRestConsumer paymentService = new PaymentRestConsumer(restUrl + restPayments, restTemplate());
        return paymentService;
    }

    @Bean
    public TicketRestConsumer ticketService() {
        TicketRestConsumer ticketService = new TicketRestConsumer(restUrl + restTickets, restTemplate());
        return ticketService;
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
