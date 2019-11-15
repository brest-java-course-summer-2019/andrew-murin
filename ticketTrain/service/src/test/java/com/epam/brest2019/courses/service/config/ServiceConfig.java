package com.epam.brest2019.courses.service.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.dao.TicketDaoJdbcImpl;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class ServiceConfig {

    @Value("${datasource.driver}")
    private String DB_DRIVER_CLASS_NAME;

    @Value("${datasource.url}")
    private String DB_URL;

    @Value("${datasource.user}")
    private String DB_USER_NAME;

    @Value("${datasource.password}")
    private String DB_PASSWORD;

    @Bean
    public PaymentDaoJdbcImpl paymentDao() throws Exception {
        PaymentDaoJdbcImpl paymentDao = new PaymentDaoJdbcImpl(sessionFactory());
        return paymentDao;
    }

    @Bean
    public PaymentServiceImpl paymentService() throws Exception {
        PaymentServiceImpl paymentService = new PaymentServiceImpl(paymentDao());
        return paymentService;
    }

    @Bean
    public TicketDaoJdbcImpl ticketDao() throws Exception {
        TicketDaoJdbcImpl ticketDao = new TicketDaoJdbcImpl(sessionFactory());
        return ticketDao;
    }

    @Bean
    public TicketServiceImpl ticketService() throws Exception {
        TicketServiceImpl ticketService = new TicketServiceImpl(ticketDao());
        return ticketService;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER_NAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() throws Exception{
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.setProperty("hibernate.hbm2ddl.auto", "create");
        builder.scanPackages("com.epam.brest2019.courses.*");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        builder.setProperty("hibernate.format_sql", "true");
        builder.setProperty("hibernate.show_sql", "true");

        return builder.buildSessionFactory();
    }
}
