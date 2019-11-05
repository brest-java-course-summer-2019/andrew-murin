package com.epam.brest2019.courses.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DataBaseConfig {

    @Value("${driver}")
    private String DRIVER;

    @Value("${user}")
    private String USER;

    @Value("${password}")
    private String PASSWORD;

    @Value("${url}")
    private String URL;

    @Autowired
    private Environment environment;

//    @Bean
//    public PaymentDao paymentDao() throws IOException {
//        PaymentDao paymentDao = new PaymentDaoJdbcImpl(sessionFactory());
//        return paymentDao;
//    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[] {"com.epam.brest2019.courses.*"}
        );
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

//    @Bean
//    public SessionFactory sessionFactory() {
//        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
//        builder.scanPackages("com.epam.brest2019.courses.dao");
//        builder.setProperty("hibernate.show_sql", "true");
//        builder.setProperty("hibernate.id.new_generator_mappings", "false");
//
//        return builder.buildSessionFactory();
//    }

//    @Bean
//    public SessionFactory sessionFactory() throws IOException {
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource());
//        sessionFactoryBean.setPackagesToScan("com.epam.brest2019.courses.dao");
//        sessionFactoryBean.afterPropertiesSet();
//        sessionFactoryBean.setHibernateProperties(hibernateProperties());
//        return sessionFactoryBean.getObject();
//    }

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
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASSWORD));

        return dataSource;
    }

    Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        return properties;
    }
}
