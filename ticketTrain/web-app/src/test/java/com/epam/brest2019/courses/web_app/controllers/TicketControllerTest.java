package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)

public class TicketControllerTest {

    @MockBean
    private TicketService ticketService;

    @Autowired
    private MockMvc mockMvc;


    private static final String TICKET_ID = "ticketId";
    private static final String CITY_FROM = "cityFrom.cityId";
    private static final String CITY_TO = "cityTo.cityId";
    private static final String TICKET_DATE = "ticketDate";
    private static final String TICKET_COST = "ticketCost";


    private static final String TICKET_START_DATE = "startDate";
    private static final String TICKET_FINISH_DATE = "finishDate";
    private static final String DIRECTION_FROM = "directionFrom";
    private static final String DIRECTION_TO = "directionTo";


    @Test
    public void gotoTicketAddPage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket"))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void findAllWithDirection() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/tickets"))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Tickets</title>")));

    }

    @Test
    public void gotoEditTicketPage() throws Exception {
        int id = 1;

        Mockito.when(ticketService.findById(Mockito.anyInt())).thenReturn(createFixture(id));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content()
                            .string(Matchers.containsString("#")));

        Mockito.verify(ticketService, times(1)).findById(Mockito.anyInt());
    }


    @Test
    public void addTicket() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/ticket")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param(TICKET_ID, "22")
                            .param(CITY_FROM, "1")
                            .param(CITY_TO, "3")
                            .param(TICKET_DATE, "2019-12-12")
                            .param(TICKET_COST, "12")
            )
                            .andExpect(MockMvcResultMatchers.status().isFound())
                            .andExpect(MockMvcResultMatchers.view().name("redirect:/tickets"))
                            .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));

    }


    @Test
    public void updateTicket() throws Exception {
        Ticket ticket = createFixture(1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/ticket/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                            .param(TICKET_ID, "1")
                            .param(CITY_FROM, "2")
                            .param(CITY_TO, "4")
                            .param(TICKET_DATE, "2020-06-11")
                            .param(TICKET_COST, "24.24")
        ).andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                        .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));


    }

    @Test
    public void deleteTicket() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/ticket/{id}/delete", 1)
                    .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isFound())
                        .andExpect(MockMvcResultMatchers.redirectedUrl("/tickets"));


    }

    @Test
    public void searchTicket() throws Exception {
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
