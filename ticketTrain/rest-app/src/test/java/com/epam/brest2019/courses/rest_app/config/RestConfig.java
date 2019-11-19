package com.epam.brest2019.courses.rest_app.config;

import com.epam.brest2019.courses.rest_app.controllers.PaymentRestController;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan(basePackageClasses = DataSourceConfig.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class RestConfig {

//    @Autowired
//    private SessionFactory sessionFactory;
//


//    @Bean
//    public PaymentService paymentService() {
//        PaymentService paymentService = new MockBean();
//        return paymentService;
//    }


}
