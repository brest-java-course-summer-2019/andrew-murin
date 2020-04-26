//package com.epam.brest2019.courses.web_app.config;
//
//import com.epam.brest2019.courses.model.Payment;
//import org.springframework.integration.annotation.Gateway;
//import org.springframework.integration.annotation.MessagingGateway;
//
//@MessagingGateway
//public interface Sender{
//    @Gateway(requestChannel = "outboundChannel")
//    Payment send(Payment message);
//}