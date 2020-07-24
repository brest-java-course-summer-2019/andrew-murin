package com.epam.brest2019.courses.model.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.model.city.CitySoap;
import com.epam.brest2019.courses.model.soap.model.ticket.TicketSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.model.soap.converter.Converter.dateConverter;
import static com.epam.brest2019.courses.model.soap.converter.Converter.dateToXML;

@Component
public class TicketConverter {

    @Autowired
    private CityConverter cityConverter;


    /**
     * Conflict ticketId and GeneratedValue in model
     *
     * @param ticket
     * @param add
     * @return
     */
    public TicketSoap ticketConverterToSoap(Ticket ticket, String add){
        TicketSoap ticketSoap = new TicketSoap();
        System.out.println(ticket);
        CitySoap cityFrom = cityConverter.cityConverterToSoap(ticket.getFromCity());
        CitySoap cityTo = cityConverter.cityConverterToSoap(ticket.getToCity());

        if (!add.equals("true")) {
            ticketSoap.setTicketId(ticket.getTicketId());
        }

        ticketSoap.setTicketDate(dateToXML(ticket.getTicketDate()));
        ticketSoap.setTicketCost(ticket.getTicketCost());

        ticketSoap.setFromCity(cityFrom);
        ticketSoap.setToCity(cityTo);

        return ticketSoap;
    }

    /**
     * Conflict ticketId and GeneratedValue in model
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

    public List<Ticket> ticketListSoapToList(List<TicketSoap> ticketSoaps) {
        List<Ticket> ticketList = new ArrayList<>();

        for (int i = 0; i < ticketSoaps.size(); i++) {
            Ticket ticket = new Ticket();
            City cityFrom = cityConverter.cityConverter(ticketSoaps.get(i).getFromCity());
            City cityTo = cityConverter.cityConverter(ticketSoaps.get(i).getToCity());

            ticket.setTicketId(ticketSoaps.get(i).getTicketId());
            ticket.setTicketDate(dateConverter(ticketSoaps.get(i).getTicketDate()));
            ticket.setTicketCost(ticketSoaps.get(i).getTicketCost());
            ticket.setFromCity(cityFrom);
            ticket.setToCity(cityTo);

            ticketList.add(ticket);
        }

        return ticketList;
    }





}
