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
@ComponentScan
@ContextConfiguration
public class DataBaseConfig {


    @Autowired
    DataSource dataSource;

    @Bean
    public SessionFactory sessionFactory() throws Exception{

        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

        builder.setProperty("hibernate.hbm2ddl.auto", "create");
        builder.scanPackages("com.epam.brest2019.courses.*");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        builder.setProperty("hibernate.format_sql", "true");
        builder.setProperty("hibernate.show_sql", "true");

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