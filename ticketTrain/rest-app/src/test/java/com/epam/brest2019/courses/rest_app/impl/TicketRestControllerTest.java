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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static com.epam.brest2019.courses.model.constant.Constant.BREST;
import static com.epam.brest2019.courses.model.constant.Constant.MINSK;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAutoConfiguration
public class TicketRestControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRestControllerTest.class);

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
        LOGGER.debug("Before");

        mockMvc = MockMvcBuilders.standaloneSetup(ticketRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
    }

    @AfterEach
    void after() {
        LOGGER.debug("After");

        Mockito.reset(ticketService);
    }

    @Test
    public void findAll() throws Exception {
        LOGGER.debug("findAll");

        Mockito.when(ticketService.findAll()).thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tickets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(jsonPath("$[0].ticketCost", Matchers.is(0)))
                .andExpect(jsonPath("$[1].ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(1)).findAll();
    }

    @Test
    public void findById() throws Exception {
        LOGGER.debug("findById");

        String id = "1";
        Mockito.when(ticketService.findById(id)).thenReturn(createFixture(1));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tickets/{ticketId}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(1)).findById(id);
    }

    @Test
    public void addTicket() throws Exception{
        LOGGER.debug("addTicket");

        Ticket ticket = createFixture(2);
        String json = new ObjectMapper().writeValueAsString(ticket);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/tickets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));

    }

    @Test
    public void updateTicket() throws Exception {
        LOGGER.debug("updateTicket");

        Ticket ticket = createFixture(2);
        String json = new ObjectMapper().writeValueAsString(ticket);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/update-tickets/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
        );
    }

    @Test
    public void deleteTicket() throws Exception {
        LOGGER.debug("deleteTicket");

        Ticket ticket = createFixture(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/tickets/{ticketId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void searchTicket() throws Exception {
        LOGGER.debug("Search Ticket");

        LocalDateTime startDate = START_DATE.atTime(LocalTime.of(1,1));
        LocalDateTime finishDate = FINISH_DATE.atTime(LocalTime.of(1,1));

        Mockito.when(ticketService.searchTicket(startDate, finishDate, BREST, MINSK))
                .thenReturn(Arrays.asList(createFixture(0), createFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/search-tickets")
                            .param("startDate", START_DATE.toString())
                            .param("finishDate", FINISH_DATE.toString())
                            .param("cityFrom", BREST)
                            .param("cityTo", MINSK)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

        Mockito.verify(ticketService).searchTicket(startDate, finishDate, BREST, MINSK);
    }


    private Ticket createFixture(int number) {
        Ticket ticket = new Ticket();
        String id = "" + number;

        ticket.setId(id);
        ticket.setTicketDate(ZonedDateTime.now());
        ticket.setFromCity(City.BREST);
        ticket.setToCity(City.MINSK);
        ticket.setTicketCost(new BigDecimal(number));

        return ticket;
    }

}
