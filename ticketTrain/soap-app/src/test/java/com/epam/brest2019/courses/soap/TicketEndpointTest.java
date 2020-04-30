//package com.epam.brest2019.courses.soap;
//
//import com.epam.brest2019.courses.model.Ticket;
//import com.epam.brest2019.courses.service.TicketService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.ws.test.server.MockWebServiceClient;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
//import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SoapApplication.class)
//public class TicketEndpointTest implements ResourceLoaderAware {
//
//    @Autowired
//    private ApplicationContext context;
//    @Autowired
//    private TicketService ticketService;
//    private MockWebServiceClient mockClient;
//    private ResourceLoader resourceLoader;
//
//
//    @Override
//    public void setResourceLoader(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    @BeforeEach
//    void createClient() {
//        mockClient = MockWebServiceClient.createClient(context);
//    }
//
//    @Test
//    @Disabled
//    void getAllTicket() throws IOException {
//        Resource request = resourceLoader.getResource("/requests/ticket/getAllTicketRequest.xml");
//        Resource response = resourceLoader.getResource("/responses/ticket/getAllTicketResponse.xml");
//
//        mockClient.sendRequest(withSoapEnvelope(request)).andExpect(soapEnvelope(response));
//    }
//
//    @Test
//    void getUpdateTicket() throws IOException {
//        Resource request = resourceLoader.getResource("/requests/ticket/getUpdateTicketRequest.xml");
//
//        Ticket ticket = ticketService.findById(1);
//
////        mockClient.sendRequest(withSoapEnvelope(request));
//
//        Ticket updatedTicket = ticketService.findById(1);
//
//        assertNotEquals(ticket, updatedTicket, "Objects are the same");
//    }
//}
