package com.epam.brest2019.courses.dao.config;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.PaymentDaoJdbcImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:db.properties", "classpath:data.sql"})
@EnableTransactionManagement
public class DataBaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public PaymentDao paymentDao() throws Exception {
        PaymentDao paymentDao = new PaymentDaoJdbcImpl(sessionFactory());
        return paymentDao;
    }

    @Bean
    public SessionFactory sessionFactory() throws Exception{
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        builder.scanPackages("com.epam.brest2019.courses.*");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        builder.setProperty("hibernate.format_sql", "true");
        builder.setProperty("hibernate.show_sql", "true");
//        builder.setProperty("hibernate.id.new_generator_mappings", "false");

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
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.user"));
        dataSource.setPassword(environment.getProperty("datasource.password"));

        return dataSource;
    }

}
