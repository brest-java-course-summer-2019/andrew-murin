package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.web_app.consumers.PaymentRestConsumer;
import com.epam.brest2019.courses.web_app.consumers.TicketRestConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.*"})
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
