package com.epam.brest2019.courses.test_db;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ContextConfiguration
@EnableTransactionManagement
public class DataBaseConfig {

//    @Value("${datasource.driver.h2}")
//    private String DB_DRIVER_CLASS_NAME;
//
//    @Value("${datasource.url.h2}")
//    private String DB_URL;
//
//    @Value("${datasource.user.h2}")
//    private String DB_USER_NAME;
//
//    @Value("${datasource.password.h2}")
//    private String DB_PASSWORD;

    @Autowired
    private Environment environment;


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

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {

//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(DB_USER_NAME);
//        dataSource.setPassword(DB_PASSWORD);
//


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_database");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

}