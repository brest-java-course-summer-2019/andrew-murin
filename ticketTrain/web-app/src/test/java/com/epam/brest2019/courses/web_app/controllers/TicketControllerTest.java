package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:app-context-test.xml")
public class TicketControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TicketService ticketService;

    private MockMvc mockMvc;


    private static final String TICKET_ID = "ticketId";
    private static final String FROM_CITY = "fromCity";
    private static final String TO_CITY = "toCity";
    private static final String TICKET_DATE = "ticketDate";
    private static final String TICKET_COST = "ticketCost";


    private static final String TICKET_START_DATE = "startDate";
    private static final String TICKET_FINISH_DATE = "finishDate";
    private static final String DIRECTION_FROM = "directionFrom";
    private static final String DIRECTION_TO = "directionTo";


    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }


    @Test
    void gotoTicketAddPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket"))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("#")));


    }

    @Test
    void findAllWithDirection() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets"))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Tickets</title>")));


    }

//    @Test
//    void gotoEditTicketPage() throws Exception {
//        int id = 1;
//
//        Mockito.when(ticketService.findById(Mockito.anyInt())).thenReturn(createFixture(id));
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/ticket/{id}", id))
//                .andDo(MockMvcResultHandlers.print())
//                        .andExpect(MockMvcResultMatchers.status().isOk())
//                        .andExpect(MockMvcResultMatchers.content()
//                            .string(Matchers.containsString("#")));
//
//
//    }

//    //TODO schould be fall
//    @Test
//    void addTicket() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/ticket")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                            .param(FROM_CITY, "1")
//                            .param(TO_CITY, "3")
//                            .param(TICKET_DATE, "2019-12-12")
//                            .param(TICKET_COST, "12")
//            )
//                            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
//
//    }


//    @Test
//    void updateTicket() throws Exception {
//        Ticket ticket = createFixture(1);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/ticket/{id}", 1)
//                    .contentType(MediaType.APPLICATION_JSON)
//                        .sessionAttr("ticketForm", ticket)
//        ).andDo(MockMvcResultHandlers.print())
//                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                        .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));
//
//    }

    @Test
    void deleteTicket() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/{id}/delete", 1)
                    .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isFound())
                        .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));


    }

    @Test
    void searchTicket() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/search-tickets")
                        .param(TICKET_START_DATE, "2019-08-12")
                        .param(TICKET_FINISH_DATE, "2019-10-01")
                        .param(DIRECTION_FROM, "1")
                        .param(DIRECTION_TO, "2"))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF8"))
                        .andExpect(MockMvcResultMatchers.view().name("tickets"));

    }


    private Ticket createFixture(Integer ticketId) {
        Ticket ticket = new Ticket();
        City city = new City();

        ticket.setTicketId(ticketId);
        ticket.setTicketDate(LocalDate.now());
        ticket.setTicketCost(new BigDecimal(15));

        city.setCityId(1);
        ticket.setFromCity(city);

        city.setCityId(2);
        ticket.setToCity(city);

        return ticket;
    }

}
