package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.consumer.config.ConsumerConfiguration;
import com.epam.brest2019.courses.model.Ticket;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.http.HttpHeaders;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.epam.brest2019.courses.consumer.serialize.SerializeClass.*;
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


    private Ticket ticket;

    private TicketRestConsumer ticketRestConsumerTest;

    @Autowired
    private RestTemplate restTemplate;

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
        List<Ticket> ticketList = convertMapperList(getJSON(TICKETS_JSON));

        assertEquals(ticket,ticketList);
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
        List<Ticket> ticketList = convertMapperList(getJSON(TICKETS_ALL_JSON));

        assertEquals(ticket,ticketList);
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
        Ticket ticketSingleList = convertMapperObject(getJSON(TICKETS_BY_ID_3_JSON), Ticket.class);

        assertEquals(ticket, ticketSingleList);
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

        wireMockRule.stubFor(get("/3")
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_BY_ID_3_JSON))));

        wireMockRule.stubFor(put("/")
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_ACCEPTED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_UPDATE_JSON)))));

        ticket = ticketRestConsumerTest.findById(3);
        ticket.setTicketCost(new BigDecimal(4.44));

        ticketRestConsumerTest.update(ticket);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/3")));
        wireMockRule.verify(putRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void searchTicket() throws IOException {
        wireMockRule.stubFor(get(urlEqualTo("/2019-09-26/2019-09-27/3/4"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_SEARCH_JSON))));

        List<Ticket> ticket = ticketRestConsumerTest.searchTicket(START_DATE, FINISH_DATE, 3,4);
        List<Ticket> ticketList = convertMapperList(getJSON(TICKETS_SEARCH_JSON));

        assertEquals(ticket, ticketList);
        wireMockRule.verify(getRequestedFor(urlEqualTo("/2019-09-26/2019-09-27/3/4")));

    }

    @Test
    public void faultPayments() {

        wireMockRule.stubFor(get(urlEqualTo("/tickets/fault"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        try {
            restTemplate.getForEntity(LOCAL_HOST + PORT + "/tickets/fault", Ticket.class);
        } catch (Exception ignored) {
        }

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets/fault")));
    }

    @Test
    public void errorByDeletePayments() {

        wireMockRule.stubFor(get(urlEqualTo("/tickets/10000000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_NOT_FOUND)));

        try {
            restTemplate.getForEntity(LOCAL_HOST + PORT + "/tickets/10000000", Ticket.class);
        } catch (Exception ignored) {

        }

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets/10000000")));
    }

}
