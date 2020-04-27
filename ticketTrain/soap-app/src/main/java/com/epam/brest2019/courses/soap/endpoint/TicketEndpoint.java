package com.epam.brest2019.courses.soap.endpoint;


import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.soap.converter.TicketConverter;
import com.epam.brest2019.courses.soap.model.ticket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class TicketEndpoint {

    private static final String TICKET_URI = "http://epam.com/brest2019/courses/soap/model/ticket";

    private final TicketService ticketService;
    private final TicketConverter converter;

    @Autowired
    public TicketEndpoint(TicketService ticketService, TicketConverter converter) {
        this.ticketService = ticketService;
        this.converter = converter;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getAllTicketRequest")
    @ResponsePayload
    public GetAllTicketResponse getAllTicketResponse(@RequestPayload GetAllTicketRequest request) {
        GetAllTicketResponse response = new GetAllTicketResponse();

        response.getListOfTicket().addAll(converter.ticketsConvertToSoapList(ticketService.findAll()));

        return response;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getTicketByIdRequest")
    @ResponsePayload
    public GetTicketByIdResponse getTicketByIdResponse(@RequestPayload GetTicketByIdRequest request) {

        GetTicketByIdResponse response = new GetTicketByIdResponse();

        response.setTicket(
                converter.ticketConverterToSoap(
                        ticketService.findById(request.getTicketId())));

        return response;
    }

//    TODO: get error ("LogicalConnectionManagedImpl from hibernate")
//    @PayloadRoot(namespace = TICKET_URI, localPart = "getAddTicketRequest")
//    @ResponsePayload
//    public void getAddTicketResponse(@RequestPayload GetAddTicketRequest request) {
//        ticketService.add(converter.ticketSoapConverterToTicket(request.getTicket()));
//    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getDeleteTicketRequest")
    @ResponsePayload
    public void getDeleteTicketResponse(@RequestPayload GetDeleteTicketRequest request) {
        ticketService.delete(request.getTicketId());
    }

}
