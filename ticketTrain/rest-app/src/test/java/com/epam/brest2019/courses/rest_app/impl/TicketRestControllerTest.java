package com.epam.brest2019.courses.rest_app.impl;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.rest_app.controllers.TicketRestController;
import com.epam.brest2019.courses.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(locations = {"classpath*:rest-spring-test.xml"})
public class TicketRestControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketRestController ticketRestController;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    private static final LocalDate START_DATE = LocalDate.of(2019,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019,12,12);

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
    }

    @AfterEach
    void after() {
        Mockito.reset(ticketService);
    }

    @Test
    public void findAll() throws Exception {
        Mockito.when(ticketService.findAll()).thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(jsonPath("$[0].ticketId", Matchers.is(0)))
                .andExpect(jsonPath("$[0].ticketCost", Matchers.is(0)))
                .andExpect(jsonPath("$[1].ticketId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(1)).findAll();
    }

    @Test
    public void findAllWithDirection() throws Exception {
        Mockito.when(ticketService.findAllWithDirection())
                .thenReturn(Arrays.asList(createFixtureForAllDirection(0),createFixtureForAllDirection(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets/find-all-with-direction")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].cityFrom", Matchers.is("MINSK0")))
                .andExpect(jsonPath("$[0].cityTo", Matchers.is("BREST0")))
                .andExpect(jsonPath("$[0].ticketId", Matchers.is(0)))
                .andExpect(jsonPath("$[1].cityFrom", Matchers.is("MINSK1")))
                .andExpect(jsonPath("$[1].cityTo", Matchers.is("BREST1")))
                .andExpect(jsonPath("$[1].ticketId", Matchers.is(1)));

        Mockito.verify(ticketService).findAllWithDirection();
    }

    @Test
    public void findById() throws Exception {
        int id = 1;
        Mockito.when(ticketService.findById(id)).thenReturn(createFixture(1));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets/{ticketId}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketId", Matchers.is(id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(1)).findById(id);
    }

    @Test
    public void addTicket() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/tickets")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(createFixtureForAllDirection(1)))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn().getResponse();


        Mockito.verify(ticketService, Mockito.times(1)).add(any(Ticket.class));
    }

    @Test
    public void updateTicket() throws Exception {
        Ticket ticket = createFixtureForAllDirection(1);
        String json = new ObjectMapper().writeValueAsString(ticket);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());

        Mockito.verify(ticketService, Mockito.times(1)).update(any());
    }

    @Test
    public void deleteTicket() throws Exception {
        Ticket ticket = createFixtureForAllDirection(1);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/tickets/{ticketId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void searchTicket() throws Exception {
        Integer directionFrom = 1;
        Integer directionTo = 2;

        Mockito.when(ticketService.searchTicket(START_DATE, FINISH_DATE, directionFrom, directionTo))
                .thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/tickets/{startDate}/{finishDate}/{directionFrom}/{directionTo}", START_DATE, FINISH_DATE, directionFrom, directionTo)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

        Mockito.verify(ticketService).searchTicket(START_DATE, FINISH_DATE, directionFrom, directionTo);
    }

    private Ticket createFixtureForAllDirection(Integer ticketId) {
        Ticket ticket = new Ticket();

        ticket.setTicketId(ticketId);
//        ticket.setCityFrom("MINSK" + ticketId);
//        ticket.setCityTo("BREST" + ticketId);

        return ticket;
    }

    private Ticket createFixture(Integer ticketId) {
        City city = new City();
        Ticket ticket = new Ticket();

        ticket.setTicketId(ticketId);
        ticket.setTicketCost(new BigDecimal(ticketId));
        ticket.setTicketDate(LocalDate.now());

        city.setCityId(1);
        ticket.setFromCity(city);

        city.setCityId(2);
        ticket.setToCity(city);

        return ticket;
    }

}
