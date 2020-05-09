//package com.epam.brest2019.courses.service.config;
//
//import com.epam.brest2019.courses.dao.PaymentDao;
//import com.epam.brest2019.courses.dao.PaymentDaoImpl;
//import com.epam.brest2019.courses.dao.TicketDao;
//import com.epam.brest2019.courses.dao.TicketDaoImpl;
//import com.epam.brest2019.courses.service.PaymentService;
//import com.epam.brest2019.courses.service.PaymentServiceImpl;
//import com.epam.brest2019.courses.service.TicketService;
//import com.epam.brest2019.courses.service.TicketServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.PropertySource;
//
//@SpringBootConfiguration
//public class ServiceConfig {
//
//    @Bean
//    public PaymentDao paymentDao() throws Exception {
//        PaymentDao paymentDao = new PaymentDaoImpl(sessionFactory);
//        return paymentDao;
//    }
//
//    @Bean
//    public PaymentService paymentService() throws Exception {
//        PaymentService paymentService = new PaymentServiceImpl(paymentDao());
//        return paymentService;
//    }
//
//    @Bean
//    public TicketDao ticketDao() throws Exception {
//        TicketDao ticketDao = new TicketDaoImpl(sessionFactory);
//        return ticketDao;
//    }
//
//    @Bean
//    public TicketService ticketService() throws Exception {
//        TicketService ticketService = new TicketServiceImpl(ticketDao());
//        return ticketService;
//    }
//
//}
