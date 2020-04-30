package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.converter.TicketConverter;
import com.epam.brest2019.courses.model.soap.model.ticket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.time.LocalDate;

import static com.epam.brest2019.courses.model.soap.converter.Converter.*;

@Component
public class TicketSoapConsumer extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketSoapConsumer.class);

    private static final String TICKET_URI = "http://localhost:8088/ws/ticket";


    @Autowired
    private TicketConverter ticketConverter;


    public GetAllTicketResponse findAll() {
        GetAllTicketRequest request = new GetAllTicketRequest();
        LOGGER.debug("Find All - {}", request);

        return (GetAllTicketResponse) getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


    public GetAllTicketWithDirectionResponse findAllWithDirection() {
        GetAllTicketWithDirectionRequest request = new GetAllTicketWithDirectionRequest();
        LOGGER.debug("Find all with direction - {}", request);

        return (GetAllTicketWithDirectionResponse) getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


    public GetSearchTicketByDateAndDirectionResponse searchTicket(LocalDate startDate, LocalDate finishDate,
                                                                  int directionFrom, int directionTo) {

        GetSearchTicketByDateAndDirectionRequest request = new GetSearchTicketByDateAndDirectionRequest();
        request.setStartDate(dateToXML(startDate));
        request.setFinishDate(dateToXML(finishDate));
        request.setDirectionFrom(directionFrom);
        request.setDirectionTo(directionTo);
        LOGGER.debug("Find ticket by date and direction - {}", request);

        return (GetSearchTicketByDateAndDirectionResponse) getWebServiceTemplate()
                .marshalSendAndReceive(TICKET_URI, request);
    }


    public GetTicketByIdResponse findTicketById(int ticketId) {
        GetTicketByIdRequest request = new GetTicketByIdRequest();
        request.setTicketId(ticketId);
        LOGGER.debug("Find ticket by id - {}", request);

        return (GetTicketByIdResponse) getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


    public void addTicket(Ticket ticket) {
        GetAddTicketRequest request = new GetAddTicketRequest();
        request.setTicket(ticketConverter.ticketConverterToSoap(ticket, ADD));
        LOGGER.debug("Add ticket - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


    public void updateTicket(Ticket ticket) {
        GetUpdateTicketRequest request = new GetUpdateTicketRequest();
        request.setTicket(ticketConverter.ticketConverterToSoap(ticket, UPDATE));
        LOGGER.debug("Update ticket - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


    public void deleteTicket(int ticketId) {
        GetDeleteTicketRequest request = new GetDeleteTicketRequest();
        request.setTicketId(ticketId);
        LOGGER.debug("Add ticket - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(TICKET_URI, request);
    }


}
