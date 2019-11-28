package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.test_db.DataBaseConfig;
import com.epam.brest2019.courses.test_db.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@EntityScan("com.epam.brest2019.courses.*")
@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.*"}, scanBasePackageClasses = {DataBaseConfig.class, DataSourceConfig.class})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
