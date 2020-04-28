package com.epam.brest2019.courses.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.soap.model.city.CitySoap;
import com.epam.brest2019.courses.soap.model.ticket.TicketSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.soap.converter.Converter.dateConverter;
import static com.epam.brest2019.courses.soap.converter.Converter.dateToXML;

@Component
public class TicketConverter {

    @Autowired
    private CityConverter cityConverter;

    public TicketSoap ticketConverterToSoap(Ticket ticket){
        TicketSoap ticketSoap = new TicketSoap();
        CitySoap cityFrom = cityConverter.cityConverterToSoap(ticket.getFromCity());
        CitySoap cityTo = cityConverter.cityConverterToSoap(ticket.getToCity());

        ticketSoap.setTicketId(ticket.getTicketId());
        ticketSoap.setTicketDate(dateToXML(ticket.getTicketDate()));
        ticket.setTicketCost(ticket.getTicketCost());

        ticketSoap.setFromCity(cityFrom);
        ticketSoap.setToCity(cityTo);

        return ticketSoap;
    }

    public List<TicketSoap> ticketsConvertToSoapList(List<Ticket> tickets) {
        List<TicketSoap> ticketSoaps = new ArrayList<>();

        for (int i = 0; i < tickets.size(); i++) {
            TicketSoap ticketSoap = new TicketSoap();
            CitySoap citySoapFrom = cityConverter.cityConverterToSoap(tickets.get(i).getFromCity());
            CitySoap citySoapTo = cityConverter.cityConverterToSoap(tickets.get(i).getToCity());

            ticketSoap.setTicketId(tickets.get(i).getTicketId());
            ticketSoap.setTicketDate(dateToXML(tickets.get(i).getTicketDate()));
            ticketSoap.setTicketCost(tickets.get(i).getTicketCost());
            ticketSoap.setFromCity(citySoapFrom);
            ticketSoap.setToCity(citySoapTo);

            ticketSoaps.add(ticketSoap);
        }

        return ticketSoaps;
    }

    /**
     * Conflict ticketId and GeneratedValue in module
     *
     * @param ticketSoap
     * @param add
     * @return
     */
    public Ticket ticketSoapConverterToTicket(TicketSoap ticketSoap, String add) {
        Ticket ticket = new Ticket();
        City cityFrom = cityConverter.cityConverter(ticketSoap.getFromCity());
        City cityTo = cityConverter.cityConverter(ticketSoap.getToCity());

        if (!add.equals("true")) {
            ticket.setTicketId(ticketSoap.getTicketId());
        }

        ticket.setTicketCost(ticketSoap.getTicketCost());
        ticket.setFromCity(cityFrom);
        ticket.setToCity(cityTo);

        ticket.setTicketDate(dateConverter(ticketSoap.getTicketDate()));

        return ticket;
    }






}
