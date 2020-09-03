
package com.epam.brest2019.courses.rest_app.impl;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.dto.TicketDto;
import com.epam.brest2019.courses.model.dto.TicketMapper;
import com.epam.brest2019.courses.rest_app.controllers.TicketRestController;
import com.epam.brest2019.courses.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import java.time.ZonedDateTime;
import java.util.Arrays;

import static com.epam.brest2019.courses.model.constant.Constant.BREST;
import static com.epam.brest2019.courses.model.constant.Constant.MINSK;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@EnableAutoConfiguration
public class TicketRestControllerTest {


    @Mock
    private TicketService ticketService;

    @Mock
    private TicketMapper mapper;

    @InjectMocks
    private TicketRestController ticketRestController;

    private MockMvc mockMvc;

    private static final LocalDate START_DATE = LocalDate.of(2010,01,01);
    private static final LocalDate FINISH_DATE = LocalDate.of(2030,12,12);

    @BeforeEach
    void before() {
        log.debug("Before");

        mockMvc = MockMvcBuilders.standaloneSetup(ticketRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
    }

    @AfterEach
    void after() {
        log.debug("After");

        Mockito.reset(ticketService);
    }

    @Test
    public void findAll() throws Exception {
        log.debug("Find all");

        Mockito.when(mapper.toDto(ticketService.findAll())).thenReturn(Arrays.asList(createFixtureDto(0), createFixtureDto(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tickets")
        ).andExpect(jsonPath("$[0].ticketCost", Matchers.is(0)))
                .andExpect(jsonPath("$[1].ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(2)).findAll();
    }

    @Test
    public void findById() throws Exception {
        log.debug("findById");

        String id = "1";
        Mockito.when(mapper.toDto(ticketService.findById(id))).thenReturn(createFixtureDto(1));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tickets/{ticketId}", 1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketCost", Matchers.is(1)));

        Mockito.verify(ticketService, Mockito.times(2)).findById(id);
    }

    @Test
    public void addTicket() throws Exception{
        log.debug("addTicket");

        Ticket ticket = createFixture(2);
        String json = new ObjectMapper().writeValueAsString(ticket);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));

    }

    @Test
    public void updateTicket() throws Exception {
        log.debug("updateTicket");

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
        log.debug("deleteTicket");

        Ticket ticket = createFixture(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/tickets/{ticketId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void searchTicket() throws Exception {
        log.debug("Search Ticket");

        String startDate = START_DATE.toString();
        String finishDate = FINISH_DATE.toString();

        Mockito.when(mapper.toDto(ticketService.searchTicket(startDate, finishDate, BREST, MINSK)))
                .thenReturn(Arrays.asList(createFixtureDto(0), createFixtureDto(1)));

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

        Mockito.verify(ticketService, Mockito.times(2)).searchTicket(startDate, finishDate, BREST, MINSK);
    }


    private Ticket createFixture(int number) {
        Ticket ticket = new Ticket();
        String id = "" + number;

        ticket.setId(id);
        ticket.setTicketDate(ZonedDateTime.now());
        ticket.setFromCity(City.BREST);
        ticket.setToCity(City.MINSK);
        ticket.setTicketCost(new BigDecimal(id));

        return ticket;
    }

    private TicketDto createFixtureDto(int number) {
        TicketDto ticket = new TicketDto();
        String id = "" + number;

        ticket.setId(id);
        ticket.setTicketDate(ZonedDateTime.now());
        ticket.setFromCity(City.BREST);
        ticket.setToCity(City.MINSK);
        ticket.setTicketCost(new BigDecimal(id));

        return ticket;
    }

}
