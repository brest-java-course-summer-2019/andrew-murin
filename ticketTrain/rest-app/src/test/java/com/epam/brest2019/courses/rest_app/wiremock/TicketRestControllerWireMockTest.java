package com.epam.brest2019.courses.rest_app.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mysql.cj.util.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
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


    private String getJSON(String path) throws IOException {
        String file = FileUtils.readFileToString(
                Objects.requireNonNull(FileUtils.toFile(TestUtils.class.getClassLoader().getResource(path))),
                String.valueOf(StandardCharsets.UTF_8));
        return file;
    }


}
