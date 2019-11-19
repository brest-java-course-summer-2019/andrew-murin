package com.epam.brest2019.courses.dao.config;


import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.TicketDaoJdbcImpl;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@Configuration
@ComponentScan(basePackageClasses = DataSourceConfig.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class DataBaseDAOConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public PaymentDao paymentDao() throws Exception {
        PaymentDao paymentDao = new PaymentDaoJdbcImpl(sessionFactory);
        return paymentDao;
    }

    @Bean
    public TicketDao ticketDao() throws Exception {
        TicketDao ticketDao = new TicketDaoJdbcImpl(sessionFactory);
        return ticketDao;
    }

}
