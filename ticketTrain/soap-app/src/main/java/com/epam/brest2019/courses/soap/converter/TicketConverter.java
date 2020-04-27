package com.epam.brest2019.courses.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.soap.model.city.CitySoap;
import com.epam.brest2019.courses.soap.model.ticket.TicketSoap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.soap.converter.Converter.dateConverter;
import static com.epam.brest2019.courses.soap.converter.Converter.dateToXML;

@Component
public class TicketConverter {


    public TicketSoap ticketConverterToSoap(Ticket ticket){
        TicketSoap ticketSoap = new TicketSoap();
        CitySoap cityFrom = new CitySoap();
        CitySoap cityTo = new CitySoap();

        ticketSoap.setTicketId(ticket.getTicketId());
        ticketSoap.setTicketDate(dateToXML(ticket.getTicketDate()));
        ticket.setTicketCost(ticket.getTicketCost());

        cityFrom.setCityId(ticket.getFromCity().getCityId());
        cityFrom.setCityName(ticket.getFromCity().getCityName());

        cityTo.setCityId(ticket.getToCity().getCityId());
        cityTo.setCityName(ticket.getToCity().getCityName());

        ticketSoap.setFromCity(cityFrom);
        ticketSoap.setToCity(cityTo);

        return ticketSoap;
    }

    public List<TicketSoap> ticketsConvertToSoapList(List<Ticket> tickets) {
        List<TicketSoap> ticketSoaps = new ArrayList<>();

        for (int i = 0; i < tickets.size(); i++) {
            TicketSoap ticketSoap = new TicketSoap();
            CitySoap citySoapFrom = new CitySoap();
            CitySoap citySoapTo = new CitySoap();

            citySoapFrom.setCityId(tickets.get(i).getFromCity().getCityId());
            citySoapFrom.setCityName(tickets.get(i).getFromCity().getCityName());

            citySoapTo.setCityId(tickets.get(i).getToCity().getCityId());
            citySoapTo.setCityName(tickets.get(i).getToCity().getCityName());

            ticketSoap.setTicketId(tickets.get(i).getTicketId());
            ticketSoap.setTicketDate(dateToXML(tickets.get(i).getTicketDate()));
            ticketSoap.setTicketCost(tickets.get(i).getTicketCost());

            ticketSoap.setFromCity(citySoapFrom);
            ticketSoap.setToCity(citySoapTo);

            ticketSoaps.add(ticketSoap);
        }

        return ticketSoaps;
    }


    public Ticket ticketSoapConverterToTicket(TicketSoap ticketSoap) {
        Ticket ticket = new Ticket();
        City cityFrom = new City();
        City cityTo = new City();

        ticket.setTicketId(ticketSoap.getTicketId());
        ticket.setTicketCost(ticketSoap.getTicketCost());

        CitySoap citySoapFrom = ticketSoap.getFromCity();
        CitySoap citySoapTo = ticketSoap.getToCity();

        cityFrom.setCityId(citySoapFrom.getCityId());
        cityFrom.setCityName(citySoapFrom.getCityName());

        cityTo.setCityId(citySoapTo.getCityId());
        cityTo.setCityName(citySoapTo.getCityName());

        ticket.setFromCity(cityFrom);
        ticket.setToCity(cityTo);

        ticket.setTicketDate(dateConverter(ticketSoap.getTicketDate()));

        return ticket;
    }






}
