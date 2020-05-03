package com.epam.brest2019.courses.soap;

import com.epam.brest2019.courses.test_db.DataBaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.model.soap.converter",
                                            "com.epam.brest2019.courses.*"},
        scanBasePackageClasses = DataBaseConfig.class)
public class SoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapApplication.class, args);
    }
}
