package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.web_app.consumers.config.ConsumerConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.http.HttpHeaders;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConsumerConfiguration.class)
public class TicketRestConsumerTest {

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    private static final String TICKETS_JSON = "mapping/tickets/tickets.json";
    private static final String TICKETS_ALL_JSON = "mapping/tickets/tickets_all.json";
    private static final String TICKETS_BY_ID_3_JSON = "mapping/tickets/tickets_by_Id_3.json";
    private static final String TICKETS_ADD_JSON = "mapping/tickets/tickets_add.json";
    private static final String TICKETS_UPDATE_JSON = "mapping/tickets/tickets_update.json";
    private static final String TICKETS_SEARCH_JSON = "mapping/tickets/tickets_search.json";

    private static final LocalDate START_DATE = LocalDate.of(2019, 9, 26);
    private static final LocalDate FINISH_DATE = LocalDate.of(2019, 9, 27);

    @Autowired
    private RestTemplate restTemplate;


    private TicketRestConsumer ticketRestConsumerTest;

    private Ticket ticket;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @Before
    public void setUp() {
        ticketRestConsumerTest = new TicketRestConsumer(LOCAL_HOST + PORT, restTemplate);
    }

    @Test
    public void findAll() throws IOException {

        wireMockRule.stubFor(get("/")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_JSON))));

        List<Ticket> ticket = ticketRestConsumerTest.findAll();
        List<Ticket> tickets = new TicketRestConsumer(LOCAL_HOST + 8088 + "/tickets", restTemplate).findAll();

        assertEquals(ticket,tickets);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void findAllWithDirection() throws IOException {

        wireMockRule.stubFor(get("/find-all-with-direction")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_ALL_JSON))));

        List<Ticket> ticket = ticketRestConsumerTest.findAllWithDirection();
        List<Ticket> tickets =
                new TicketRestConsumer(LOCAL_HOST + 8088 + "/tickets", restTemplate).findAllWithDirection();

        assertEquals(tickets,ticket);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/find-all-with-direction")));
    }

    @Test
    public void findById() throws IOException {

        wireMockRule.stubFor(get("/3")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_BY_ID_3_JSON))));

        Ticket ticket = ticketRestConsumerTest.findById(3);
        Ticket tickets = new TicketRestConsumer(LOCAL_HOST + 8088 + "/tickets", restTemplate).findById(3);

        assertEquals(ticket, tickets);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/3")));
    }

    @Test
    public void add() throws IOException {

        wireMockRule.stubFor(post("/")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_ADD_JSON)))));

        ticketRestConsumerTest.add(ticket);

        wireMockRule.verify(postRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void deleteTicket() {

        wireMockRule.stubFor(delete("/1")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT))));

        ticketRestConsumerTest.delete(1);

        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/1")));
    }

    @Test
    public void update() throws IOException {

        wireMockRule.stubFor(put("/2")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_ACCEPTED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_UPDATE_JSON)))));

        Ticket ticket = new TicketRestConsumer(LOCAL_HOST + 8088 + "/tickets", restTemplate).findById(2);
        ticket.setTicketCost(new BigDecimal(7.55));
        ticketRestConsumerTest.update(ticket);

        wireMockRule.verify(putRequestedFor(urlEqualTo("/2")));
    }

    @Test
    public void searchTicket() throws IOException {
        wireMockRule.stubFor(get(urlEqualTo("/2019-09-26/2019-09-27/3/4"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_SEARCH_JSON))));

        List<Ticket> ticket = ticketRestConsumerTest.searchTicket(START_DATE, FINISH_DATE, 3,4);
        List<Ticket> tickets = new TicketRestConsumer(LOCAL_HOST + 8088 + "/tickets", restTemplate)
                .searchTicket(START_DATE, FINISH_DATE, 3,4);

        assertEquals(ticket, tickets);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/2019-09-26/2019-09-27/3/4")));

    }


    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }



}
