package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Payment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.Collections;

@EnableIntegration
@IntegrationComponentScan(basePackages = "com.epam.brest2019.courses.*")
@SpringBootApplication(scanBasePackages = "com.epam.brest2019.courses.*")
@EnableJms
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("content-type");
        converter.setTypeIdMappings(Collections.singletonMap("payment", Payment.class));
        return converter;
    }

}
