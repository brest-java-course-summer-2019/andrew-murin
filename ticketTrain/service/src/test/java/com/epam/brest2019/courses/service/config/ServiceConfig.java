package com.epam.brest2019.courses.service.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.TicketDaoJdbcImpl;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import com.epam.brest2019.courses.test_db.DataBaseConfig;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = {DataSourceConfig.class, DataBaseConfig.class})
@PropertySource("classpath:application-test.properties")
public class ServiceConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public PaymentDao paymentDao() throws Exception {
        PaymentDao paymentDao = new PaymentDaoJdbcImpl(sessionFactory);
        return paymentDao;
    }

    @Bean
    public PaymentService paymentService() throws Exception {
        PaymentService paymentService = new PaymentServiceImpl(paymentDao());
        return paymentService;
    }

    @Bean
    public TicketDao ticketDao() throws Exception {
        TicketDao ticketDao = new TicketDaoJdbcImpl(sessionFactory);
        return ticketDao;
    }

    @Bean
    public TicketService ticketService() throws Exception {
        TicketService ticketService = new TicketServiceImpl(ticketDao());
        return ticketService;
    }

}
