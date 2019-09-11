package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class TicketRestControllerTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRestController ticketRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(ticketRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
   }

    @AfterEach
    public void after(){
        Mockito.reset(ticketService);
    }

    @Test
    public void Tickets() throws Exception {
        Mockito.when(ticketService.findAll()).thenReturn(Arrays.asList(createTicketFixture(0), createTicketFixture(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticketDirection", Matchers.is("To0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticketId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].ticketDirection", Matchers.is("To1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].ticketId", Matchers.is(1)));

        Mockito.verify(ticketService).findAll();
    }

    @Test
    public void shouldPersistTicket() throws Exception {
        Ticket expectedTicket = createTicketFixture(2);

        Ticket inputTicket = new Ticket()
                .setTicketDirection(expectedTicket.getTicketDirection());

        String json = new ObjectMapper().writeValueAsString(inputTicket);

        Mockito.when(ticketService.add(any(Ticket.class))).thenReturn(expectedTicket);

        MockHttpServletResponse response = mockMvc.perform(
                post("/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        String content = response.getContentAsString();
        Ticket result = objectMapper.readValue(content, Ticket.class);
        assertEquals(expectedTicket.getTicketDirection(), result.getTicketDirection());
        assertEquals(expectedTicket.getTicketId(), result.getTicketId());
    }

    @Test
    public void schouldUpdateTicket() throws Exception {
        Ticket ticket = createTicketFixture(1);
        String json = new ObjectMapper().writeValueAsString(ticket);

        mockMvc.perform(
                put("/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());
    }


    private Ticket createTicketFixture(int index){
        Ticket ticket = new Ticket();
        ticket.setTicketDirection("To" + index);
        ticket.setTicketId(index);
        return ticket;
    }

}
