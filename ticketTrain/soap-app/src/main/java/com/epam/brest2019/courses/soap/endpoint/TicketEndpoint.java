package com.epam.brest2019.courses.soap.endpoint;


import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.soap.converter.Converter;
import com.epam.brest2019.courses.soap.model.ticket.GetAllTicketRequest;
import com.epam.brest2019.courses.soap.model.ticket.GetAllTicketResponse;
import com.epam.brest2019.courses.soap.model.ticket.GetTicketByIdRequest;
import com.epam.brest2019.courses.soap.model.ticket.GetTicketByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class TicketEndpoint {

    private static final String TICKET_URI = "http://epam.com/brest2019/courses/soap/model/ticket";

    private final TicketService ticketService;
    private final Converter converter;

    @Autowired
    public TicketEndpoint(TicketService ticketService, Converter converter) {
        this.ticketService = ticketService;
        this.converter = converter;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getAllTicketRequest")
    @ResponsePayload
    public GetAllTicketResponse getAllTicketResponse(@RequestPayload GetAllTicketRequest request) {
        GetAllTicketResponse response = new GetAllTicketResponse();

        response.getListOfTicket().addAll(converter.ticketSoapList(ticketService.findAll()));

        return response;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getTicketByIdRequest")
    @ResponsePayload
    public GetTicketByIdResponse getTicketByIdResponse(@RequestPayload GetTicketByIdRequest request) {

        GetTicketByIdResponse response = new GetTicketByIdResponse();

        Ticket ticket = ticketService.findById(request.getTicketId());

        response.setTicket(
                converter.ticketConverterToSoap(
                        ticketService.findById(request.getTicketId())));

        return response;
    }

}
