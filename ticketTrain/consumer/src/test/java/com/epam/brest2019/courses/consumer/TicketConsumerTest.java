package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.consumer.config.ConsumerSoapConfig;
import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.converter.TicketConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.client.MockWebServiceServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.epam.brest2019.courses.model.soap.converter.Converter.ADD;
import static com.epam.brest2019.courses.model.soap.converter.Converter.UPDATE;
import static org.springframework.ws.test.client.RequestMatchers.soapEnvelope;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConsumerSoapConfig.class)
public class TicketConsumerTest implements ResourceLoaderAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumerTest.class);

    @Autowired
    private TicketSoapConsumer ticketSoapConsumer;
    @Autowired
    private TicketConverter ticketConverter;
    private MockWebServiceServer mockServer;
    private ResourceLoader resourceLoader;


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @BeforeEach
    public void createSoapConsumer() {
        mockServer = MockWebServiceServer.createServer(ticketSoapConsumer);
    }

    @Test
    @Order(1)
    void getAllTicket() throws IOException {
        Resource request = resourceLoader.getResource("classpath:/requests/ticket/getAllTicketRequest.xml");
        Resource response = resourceLoader.getResource("classpath:/responses/ticket/getAllTicketResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        ticketSoapConsumer.findAll();

        mockServer.verify();
    }

    @Test
    @Order(2)
    void getAllTicketWithDirections() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getAllTicketWithDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/ticket/getAllTicketWithDirectionResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        ticketSoapConsumer.findAllWithDirection();

        mockServer.verify();
    }

    @Test
    @Order(3)
    void getSearchByDate() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getSearchTicketByDateAndDirectionRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/ticket/getSearchTicketByDateAndDirectionResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        LocalDate startDate = LocalDate.of(2019, 9, 22);
        LocalDate finishDate = LocalDate.of(2019, 9, 27);

        ticketSoapConsumer.searchTicket(startDate, finishDate, 1, 6);

        mockServer.verify();

    }

    @Test
    @Order(4)
    void getTicketById() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getTicketByIdRequest.xml");
        Resource response = resourceLoader
                .getResource("classpath:/responses/ticket/getTicketByIdResponse.xml");

        mockServer.expect(soapEnvelope(request))
                .andRespond(withSoapEnvelope(response));

        ticketSoapConsumer.findTicketById(1);

        mockServer.verify();
    }


    @Test
    void getAddTicket() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getAddTicketRequest.xml");

        Ticket ticket = createTicketFixture(ADD);

        mockServer.expect(soapEnvelope(request));

        ticketSoapConsumer.addTicket(ticket);

        mockServer.verify();
    }

    @Test
    void getUpdateTicket() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getUpdateTicketRequest.xml");

        mockServer.expect(soapEnvelope(request));

        Ticket ticket = createTicketFixture(UPDATE);

        ticketSoapConsumer.updateTicket(ticket);

        mockServer.verify();
    }

    @Test
    void getDeleteTicket() throws IOException {
        Resource request = resourceLoader
                .getResource("classpath:/requests/ticket/getDeleteTicketRequest.xml");

        mockServer.expect(soapEnvelope(request));

        ticketSoapConsumer.deleteTicket(8);

        mockServer.verify();
    }

    private Ticket createTicketFixture(String add) {
        Ticket ticket = new Ticket();
        City fromCity = new City();
        City toCity = new City();

        fromCity.setCityId(6);
        fromCity.setCityName("MOGILEV");
        toCity.setCityId(3);
        toCity.setCityName("VITEBSK");

        ticket.setTicketId(1);
        ticket.setTicketCost(new BigDecimal("20"));
        ticket.setTicketDate(LocalDate.of(2030, 5, 5));
        ticket.setFromCity(fromCity);
        ticket.setToCity(toCity);

        return ticket;
    }

}
