package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.dao.PaymentDao;
import com.epam.brest2019.courses.dao.TicketDao;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.PaymentServiceImpl;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("com.epam.brest2019.courses.*")
@SpringBootApplication(scanBasePackages = {"com.epam.brest2019.courses.*"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

//    @Autowired
//    private TicketDao ticketDao;
//
//    @Autowired
//    private PaymentDao paymentDao;
//
//    @Bean
//    public TicketService ticketService() {
//        TicketService ticketService = new TicketServiceImpl(ticketDao);
//        return ticketService;
//    }
//
//    @Bean
//    public PaymentService paymentService() {
//        PaymentService paymentService = new PaymentServiceImpl(paymentDao);
//        return paymentService;
//    }

}
