//package com.epam.brest2019.courses.web_app.controllers;
//
//import com.epam.brest2019.courses.model.Payment;
//import com.epam.brest2019.courses.service.PaymentService;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = "classpath*:app-context-test.xml")
//public class PaymentControllerTest {
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    private static final String PAYMENT_ID = "paymentId";
//    private static final String PAYMENT_DATE = "paymentDate";
//    private static final String PAID_TICKET_DATE = "paidTicketDate";
//    private static final String TICKET_ID = "ticketId";
//
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    void paidTickets() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/paid-tickets"))
//                .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
//                    .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Paid tickets</title>")));
//
//    }
//
//    @Test
//    void gotoEditPaidTicketPage() throws Exception {
//        int id = 1;
//
//        Mockito.when(paymentService.findById(Mockito.anyInt())).thenReturn(createFixture(id));
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/paid-ticket/{id}", id))
//                .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.content()
//                        .string(Matchers.containsString("#")));
//
//    }
//
//    @Test
//    void payTicket() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/pay-ticket/1")
//        ).andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));
//    }
//
//    @Test
//    void updatePaidTicket() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/paid-ticket/{id}", 1)
//                    .contentType(MediaType.APPLICATION_JSON)
//                        .param(PAID_TICKET_DATE, "2020-09-25")
//                        .param(PAYMENT_ID, "1")
//                        .param(TICKET_ID, "1")
//                        .param(PAYMENT_DATE, "2020-09-25")
//        ).andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/paid-tickets"));
//    }
//
//    @Test
//    void deletePayment() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/paid-ticket/{id}/delete", 1)
//                    .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(MockMvcResultMatchers.status().isFound())
//                        .andExpect(MockMvcResultMatchers.redirectedUrl("/paid-tickets"))
//                        .andExpect(MockMvcResultMatchers.view().name("redirect:/paid-tickets"));
//
//        Mockito.verify(paymentService, Mockito.times(1)).delete(Mockito.anyInt());
//    }
//
//    @Test
//    void searchByDate() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/paid-tickets"))
//                .andDo(MockMvcResultHandlers.print())
//                        .andExpect(MockMvcResultMatchers.status().isOk())
//                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
//                        .andExpect(MockMvcResultMatchers.view().name("paid-tickets"))
//                        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Paid tickets</title>")));
//    }
//
//    private Payment createFixture(Integer id) {
//        Payment payment = new Payment();
//        payment.setPaymentId(id);
//        return payment;
//    }
//
//
//
//}
