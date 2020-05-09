//package com.epam.brest2019.courses.dao.config;
//
//
//import com.epam.brest2019.courses.dao.PaymentDao;
//import com.epam.brest2019.courses.dao.PaymentDaoImpl;
//import com.epam.brest2019.courses.dao.TicketDao;
//import com.epam.brest2019.courses.dao.TicketDaoImpl;
//import com.epam.brest2019.courses.test_db.DataBaseConfig;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//
//
//@SpringBootConfiguration
//@ComponentScan(basePackageClasses = DataBaseConfig.class)
//@ContextConfiguration(classes = DataBaseConfig.class)
//@TestPropertySource("classpath:application-test.properties")
//public class DataBaseDAOConfig {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Bean
//    public PaymentDao paymentDao() throws Exception {
//        PaymentDao paymentDao = new PaymentDaoImpl(sessionFactory);
//        return paymentDao;
//    }
//
//    @Bean
//    public TicketDao ticketDao() throws Exception {
//        TicketDao ticketDao = new TicketDaoImpl(sessionFactory);
//        return ticketDao;
//    }
//
//}
