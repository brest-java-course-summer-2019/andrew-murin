//package com.epam.brest2019.courses.service.config;
//
//import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
//import com.epam.brest2019.courses.dao.TicketDaoJdbcImpl;
//import com.epam.brest2019.courses.service.PaymentServiceImpl;
//import com.epam.brest2019.courses.service.TicketServiceImpl;
//import com.epam.brest2019.courses.test_db.DataBaseConfig;
//import com.epam.brest2019.courses.test_db.DataSourceConfig;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@ComponentScan(basePackageClasses = {DataSourceConfig.class, DataBaseConfig.class})
//@PropertySource("classpath:application-dev.properties")
//public class ServiceConfig {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Bean
//    public PaymentDaoJdbcImpl paymentDao() throws Exception {
//        PaymentDaoJdbcImpl paymentDao = new PaymentDaoJdbcImpl(sessionFactory);
//        return paymentDao;
//    }
//
//    @Bean
//    public PaymentServiceImpl paymentService() throws Exception {
//        PaymentServiceImpl paymentService = new PaymentServiceImpl(paymentDao());
//        return paymentService;
//    }
//
//    @Bean
//    public TicketDaoJdbcImpl ticketDao() throws Exception {
//        TicketDaoJdbcImpl ticketDao = new TicketDaoJdbcImpl(sessionFactory);
//        return ticketDao;
//    }
//
//    @Bean
//    public TicketServiceImpl ticketService() throws Exception {
//        TicketServiceImpl ticketService = new TicketServiceImpl(ticketDao());
//        return ticketService;
//    }
//
//}
