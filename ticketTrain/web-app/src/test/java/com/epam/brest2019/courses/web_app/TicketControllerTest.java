//package com.epam.brest2019.courses.web_app;
//
//import com.epam.brest2019.courses.service.TicketService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
//public class TicketControllerTest {
//
//    private static final String TICKET = "ticket";
//    private static final String TICKETS = "tickets";
//    private static final String TICKET_ID = "ticketId";
//    private static final String TICKET_DIRECTION = "ticketDirection";
//    private static final Integer COST = 10;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private TicketService ticketService;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                .build();
//    }
//
//}
