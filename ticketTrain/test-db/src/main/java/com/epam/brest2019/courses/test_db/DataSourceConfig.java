package com.epam.brest2019.courses.test_db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@ComponentScan("com.epam.brest2019.courses.*")
@Configuration
public class DataSourceConfig {
/*
    @Profile("dev")
    @Bean
    public static PropertySourcesPlaceholderConfigurer dataSourceH2() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("application-dev.properties"));
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }

    @Profile("prod")
    @Bean
    public static PropertySourcesPlaceholderConfigurer dataSourceMySQL() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("application.properties"));
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }
*/

}
