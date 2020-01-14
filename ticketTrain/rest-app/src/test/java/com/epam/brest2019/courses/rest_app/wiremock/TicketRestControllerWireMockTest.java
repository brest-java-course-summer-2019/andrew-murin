package com.epam.brest2019.courses.rest_app.wiremock;

import com.epam.brest2019.courses.model.Ticket;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.org.apache.commons.io.FileUtils;
import wiremock.org.apache.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketRestControllerWireMockTest {

    private static final int PORT = 9999;
    private static final String LOCAL_HOST = "http://localhost:";

    private static final String TICKETS_JSON = "mapping/tickets/tickets.json";
    private static final String TICKETS_ALL_JSON = "mapping/tickets/tickets_all.json";
    private static final String TICKETS_BY_ID_3_JSON = "mapping/tickets/tickets_by_Id_3.json";
    private static final String TICKETS_ADD_JSON = "mapping/tickets/tickets_add.json";
    private static final String TICKETS_UPDATE_JSON = "mapping/tickets/tickets_update.json";
    private static final String TICKETS_SEARCH_JSON = "mapping/tickets/tickets_search.json";


    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);


    @Test
    public void whenRequestOnTickets() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/tickets"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_JSON))));

        ResponseEntity tickets = restTemplate.getForEntity(LOCAL_HOST + PORT + "/tickets", String.class);

        assertNotNull(tickets.getBody());
        assertEquals(tickets.getBody(), getJSON(TICKETS_JSON));
        assertEquals(tickets.getStatusCodeValue(), 200);
        assertEquals(tickets.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets")));
    }

    @Test
    public void whenRequestGetFindTicketsWithDirections() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/tickets/find-all-with-direction"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_ALL_JSON))));

        ResponseEntity tickets = restTemplate
                .getForEntity(LOCAL_HOST + PORT + "/tickets/find-all-with-direction", String.class);

        assertNotNull(tickets.getBody());
        assertEquals(tickets.getBody(), getJSON(TICKETS_ALL_JSON));
        assertEquals(tickets.getStatusCodeValue(), 200);
        assertEquals(tickets.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets/find-all-with-direction")));
    }

    @Test
    public void whenRequestOnFindTicketById() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/tickets/3"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_BY_ID_3_JSON))));

        ResponseEntity tickets = restTemplate.getForEntity(LOCAL_HOST + PORT + "/tickets/3", String.class);

        assertNotNull(tickets.getBody());
        assertEquals(tickets.getBody(), getJSON(TICKETS_BY_ID_3_JSON));
        assertEquals(tickets.getStatusCodeValue(), 200);
        assertEquals(tickets.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets/3")));
    }

    @Test
    public void whenRequestOnCreateTicket() throws IOException {

        wireMockRule.stubFor(post(urlEqualTo("/tickets"))
                .willReturn((aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_ADD_JSON)))));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> paymentAdd = new HttpEntity<>(getJSON(TICKETS_ADD_JSON), headers);

        ResponseEntity paidTicket = restTemplate
                .postForEntity(LOCAL_HOST + PORT + "/tickets", paymentAdd, String.class);

        assertNotNull(paidTicket.getBody());
        assertEquals(paidTicket.getBody(), getJSON(TICKETS_ADD_JSON));
        assertEquals(paidTicket.getStatusCodeValue(), 201);
        assertEquals(paidTicket.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(postRequestedFor(urlEqualTo("/tickets")));

    }

//    //TODO trouble run together with searchByDate
//    @Test
//    public void whenRequestOnUpdateTicket() throws IOException {
//
//        wireMockRule.stubFor(put(urlEqualTo("/tickets"))
//                .willReturn((aResponse()
//                        .withStatus(HttpStatus.SC_OK)
//                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
//                        .withBody(getJSON(TICKETS_UPDATE_JSON)))));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> ticketUpdate = new HttpEntity<>(getJSON(TICKETS_UPDATE_JSON), headers);
//
//        restTemplate.put(LOCAL_HOST + PORT + "/tickets", ticketUpdate);
//
//        wireMockRule.verify(putRequestedFor(urlEqualTo("/tickets")));
//    }



    @Test
    public void whenRequestOnDeleteTicket() {

        wireMockRule.stubFor(delete(urlEqualTo("/tickets/2"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT)));

        restTemplate.delete(LOCAL_HOST + PORT + "/tickets/2", String.class);

        wireMockRule.verify(deleteRequestedFor(urlEqualTo("/tickets/2")));
    }

    @Test
    public void whenRequestOnSearchTicketsByDateAndDirections() throws IOException {

        wireMockRule.stubFor(get(urlEqualTo("/tickets/2019-09-26/2019-09-27/3/4"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withBody(getJSON(TICKETS_SEARCH_JSON))));

        ResponseEntity searchPayment = restTemplate
                .getForEntity(LOCAL_HOST + PORT + "/tickets/2019-09-26/2019-09-27/3/4", String.class);

        assertNotNull(searchPayment.getBody());
        assertEquals(searchPayment.getBody(), getJSON(TICKETS_SEARCH_JSON));
        assertEquals(searchPayment.getStatusCodeValue(), 200);
        assertEquals(searchPayment.getHeaders().getContentType().toString(), APPLICATION_JSON_UTF8_VALUE);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/tickets/2019-09-26/2019-09-27/3/4")));
    }


    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }
}
