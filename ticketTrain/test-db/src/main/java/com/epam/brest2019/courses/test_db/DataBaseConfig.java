package com.epam.brest2019.courses.test_db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.epam.brest2019.courses.*")
@ContextConfiguration
public class DataBaseConfig {


    @Autowired
    private DataSource dataSource;

    @Bean
    public SessionFactory sessionFactory() throws Exception{

        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("com.epam.brest2019.courses.*");

        return builder.buildSessionFactory();
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

}