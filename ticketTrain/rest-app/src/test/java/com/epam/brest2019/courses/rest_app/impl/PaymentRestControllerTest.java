package com.epam.brest2019.courses.rest_app.impl;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.rest_app.controllers.PaymentRestController;
import com.epam.brest2019.courses.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class PaymentRestControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRestControllerTest.class);


    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentRestController paymentRestController;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
    }

    @AfterEach
    void after() {
        Mockito.reset(paymentService);
    }

    @Test
    void findAll() throws Exception {

        Mockito.when(paymentService.findAll()).thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].paymentId", Matchers.is(1)));

        Mockito.verify(paymentService, Mockito.times(1)).findAll();
    }

    @Test
    void findAllWitchDirection() throws Exception {
        Mockito.when(paymentService.findAllWitchDirection()).thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments/find-All-With-Direction")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].paymentId", Matchers.is(1)));

        Mockito.verify(paymentService, Mockito.times(1)).findAllWitchDirection();
    }

    @Test
    void findByTicketId() throws Exception {
        int id = 1;
        Mockito.when(paymentService.findByTicketId(id)).thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments/ticket/{ticketId}", id)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].paymentId", Matchers.is(1)));

        Mockito.verify(paymentService, Mockito.times(1)).findByTicketId(id);
    }

    @Test
    void findById() throws Exception {
        int id = 1;
        Mockito.when(paymentService.findById(id)).thenReturn((createFixture(id)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments/{paymentId}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId").value(1));

        Mockito.verify(paymentService, Mockito.times(1)).findById(id);
    }

    @Test
    void addPayment() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/payments")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(createFixture(1)))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        Mockito.verify(paymentService, Mockito.times(1)).add(any());
    }
//
    @Test
    void updatePayment() throws Exception {
        Payment payment = createFixture(1);
        String json = objectMapper.writeValueAsString(payment);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());
    }
//
//    @Test
//    void deletePayment() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/payments/{paymentId}", 1))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        Mockito.verify(paymentService, Mockito.times(1)).delete(any());
//
//    }

    @Test
    void searchByDate() throws Exception {
        Mockito.when(paymentService.searchByDate(START_DATE, FINISH_DATE)).thenReturn(Arrays.asList(createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments/{startDate}/{finishDate}", START_DATE, FINISH_DATE)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

        Mockito.verify(paymentService, Mockito.times(1)).searchByDate(START_DATE, FINISH_DATE);
    }

    private Payment createFixture(Integer paymentId) {
        Payment payment = new Payment();
        Ticket ticket = new Ticket();
        City city = new City();

        ticket.setTicketId(1);
        ticket.setTicketCost(new BigDecimal(10));
        ticket.setTicketDate(START_DATE);

        city.setCityId(1);
        ticket.setFromCity(city);

        city.setCityId(2);
        ticket.setToCity(city);

        payment.setPaymentId(paymentId);
        payment.setTicketId(ticket);
        payment.setPaymentDate(LocalDate.now());
        return payment;
    }

}
