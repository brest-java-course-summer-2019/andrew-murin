package com.epam.brest2019.courses.soap;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ws.test.server.MockWebServiceClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;


@SpringBootTest(classes = SoapApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketEndpointTest implements ResourceLoaderAware {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private TicketService ticketService;
    private MockWebServiceClient mockClient;
    private ResourceLoader resourceLoader;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @BeforeEach
    void createClient() {
        mockClient = MockWebServiceClient.createClient(context);
    }

    @Test
    void getAllTicket() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/ticket/getAllTicketRequest.xml");
        Resource response = resourceLoader.getResource("classpath:/responses/ticket/getAllTicketResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    void getAllTicketWithDirection() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getAllTicketWithDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/ticket/getAllTicketWithDirectionResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    @Order(1)
    void getSearchTicket() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getSearchTicketByDateAndDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/ticket/getSearchTicketByDateAndDirectionResponse.xml");

        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
    }

    @Test
    void getUpdateTicket() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/ticket/getUpdateTicketRequest.xml");

        Ticket ticket = ticketService.findById(1);

        mockClient.sendRequest(withSoapEnvelope(request));

        Ticket updatedTicket = ticketService.findById(1);

        assertNotEquals(ticket, updatedTicket, "Tickets are the same");
    }

    @Test
    void getAddTicket() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/ticket/getAddTicketRequest.xml");

        int sizeBefore = ticketService.findAll().size();

        mockClient.sendRequest(withSoapEnvelope(request));

        int sizeAfter = ticketService.findAll().size();

        assertEquals(sizeBefore + 1, sizeAfter, "Sizes are different");
    }

    @Test
    void getDeleteTicket() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/ticket/getDeleteTicketRequest.xml");

        int sizeBefore = ticketService.findAll().size();

        mockClient.sendRequest(withSoapEnvelope(request));

        int sizeAfter = ticketService.findAll().size();

        assertEquals(sizeBefore, sizeAfter + 1, "Sizes are the same");
    }
}
