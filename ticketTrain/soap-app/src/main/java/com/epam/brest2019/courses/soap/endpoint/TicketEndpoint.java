package com.epam.brest2019.courses.soap.endpoint;


import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.soap.converter.TicketConverter;
import com.epam.brest2019.courses.soap.model.ticket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;
import java.util.List;

import static com.epam.brest2019.courses.soap.converter.Converter.*;

/**
 * TicketEndpoint, receives and sends objects in xml
 */
@Endpoint
public class TicketEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketEndpoint.class);

    private static final String TICKET_URI = "http://epam.com/brest2019/courses/soap/model/ticket";

    private final TicketService ticketService;
    private final TicketConverter ticketConverter;

    @Autowired
    public TicketEndpoint(TicketService ticketService, TicketConverter ticketConverter) {
        LOGGER.debug("Initialize constructor, ticketService - {}, ticketConverter - {}"
                                            ,ticketService, ticketConverter);
        this.ticketService = ticketService;
        this.ticketConverter = ticketConverter;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getAllTicketRequest")
    @ResponsePayload
    public GetAllTicketResponse getAllTicketResponse(@RequestPayload GetAllTicketRequest request) {
        LOGGER.debug("GetAllTicketRequest - {}", request);
        GetAllTicketResponse response = new GetAllTicketResponse();

        response.getListOfTicket().addAll(ticketConverter.ticketsConvertToSoapList(ticketService.findAll()));

        return response;
    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getAllTicketWithDirectionRequest")
    @ResponsePayload
    public GetAllTicketWithDirectionResponse getAllTicketWithDirectionResponse(
                                                            @RequestPayload GetAllTicketWithDirectionRequest request) {
        LOGGER.debug("GetAllTicketWithDirectionRequest - {}", request);
        GetAllTicketWithDirectionResponse response = new GetAllTicketWithDirectionResponse();

        response.getListOfTicket().addAll(
                ticketConverter.ticketsConvertToSoapList(ticketService.findAllWithDirection()));

        int size = ticketService.findAllWithDirection().size();

        return response;
    }


    @PayloadRoot(namespace = TICKET_URI, localPart = "getTicketByIdRequest")
    @ResponsePayload
    public GetTicketByIdResponse getTicketByIdResponse(@RequestPayload GetTicketByIdRequest request) {
        LOGGER.debug("GetTicketByIdRequest - {}", request);
        GetTicketByIdResponse response = new GetTicketByIdResponse();

        response.setTicket(
                ticketConverter.ticketConverterToSoap(
                        ticketService.findById(request.getTicketId())));

        return response;
    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getAddTicketRequest")
    public void getAddTicketResponse(@RequestPayload GetAddTicketRequest request) {
        LOGGER.debug("GetAddTicketRequest - {}", request);
        ticketService.add(ticketConverter.ticketSoapConverterToTicket(request.getTicket(), ADD));
    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getUpdateTicketRequest")
    public void getUpdateTicketResponse(@RequestPayload GetUpdateTicketRequest request) {
        LOGGER.debug("GetUpdateTicketRequest - {}", request);
        ticketService.update(ticketConverter.ticketSoapConverterToTicket(request.getTicket(), UPDATE));
    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getDeleteTicketRequest")
    public void getDeleteTicketResponse(@RequestPayload GetDeleteTicketRequest request) {
        LOGGER.debug("GetDeleteTicketRequest - {}", request);
        ticketService.delete(request.getTicketId());
    }

    @PayloadRoot(namespace = TICKET_URI, localPart = "getSearchTicketByDateAndDirectionRequest")
    @ResponsePayload
    public GetSearchTicketByDateAndDirectionResponse searchByDateAndDirectionResponse(
                                                    @RequestPayload GetSearchTicketByDateAndDirectionRequest request) {
        LOGGER.debug("GetSearchTicketByDateAndDirectionRequest - {}", request);
        GetSearchTicketByDateAndDirectionResponse response = new GetSearchTicketByDateAndDirectionResponse();

        try {
            LocalDate startDate = dateConverter(request.getStartDate());
            LocalDate finishDate = dateConverter(request.getFinishDate());
            int directionFrom = request.getDirectionFrom();
            int directionTo = request.getDirectionTo();

            List<Ticket> ticketList = ticketService.searchTicket(startDate, finishDate, directionFrom, directionTo);

            response.getListOfTicket().addAll(ticketConverter.ticketsConvertToSoapList(ticketList));
        } catch (Exception ex) {
            LOGGER.debug("Exception ex: {}", ex.getMessage());
        }

        return response;
    }

}
