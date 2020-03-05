package com.epam.brest2019.courses.service.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.config.DaoConfig;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@Configuration
@ComponentScan("com.epam.brest2019.courses.*")
@EnableAutoConfiguration
@ContextConfiguration(classes = DaoConfig.class)
public class ServiceConfig {

    @Autowired
    public PaymentDao paymentDao;

    @Autowired
    public TicketDao ticketDao;

    @Bean
    public PaymentService paymentService() {
        return new PaymentServiceImpl(paymentDao);
    }

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImpl(ticketDao);
    }

}
