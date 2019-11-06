package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
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

    @Autowired
    private Environment environment;

    @Bean
    public PaymentDao paymentDao() throws IOException {
        PaymentDao paymentDao = new PaymentDaoJdbcImpl(sessionFactory());
        return paymentDao;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(
//                new String[] {"com.epam.brest2019.courses.model"}
//        );
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
//        builder.setProperty("hibernate.connection.url", URL);
//        builder.setProperty("hibernate.connection.username", USER);
//        builder.setProperty("hibernate.connection.password", PASSWORD);
//        builder.setProperty("hibernate.hbm2ddl.auto", "hibernate.dialect");
        builder.scanPackages("com.epam.brest2019.courses.*");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        builder.setProperty("hibernate.format_sql", "hibernate.format_sql");
        builder.setProperty("hibernate.show_sql", "true");
//        builder.setProperty("hibernate.id.new_generator_mappings", "false");

        return builder.buildSessionFactory();
    }

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
        dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.user"));
        dataSource.setPassword(environment.getProperty("datasource.password"));

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
