package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Payment;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(Payment.class)
@MapperScan("com.epam.brest2019.courses.*")
@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.*"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
