package com.epam.brest2019.courses.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.soap.model.city.CitySoap;
import com.epam.brest2019.courses.soap.model.payment.PaymentSoap;
import com.epam.brest2019.courses.soap.model.ticket.TicketSoap;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    public CitySoap cityConverterToSoap(City city) {
        CitySoap citySoap = new CitySoap();

        citySoap.setCityId(city.getCityId());
        citySoap.setCityName(city.getCityName());

        return citySoap;
    }

    public PaymentSoap paymentConverterToSoap(Payment payment) {
        PaymentSoap paymentSoap = new PaymentSoap();

        paymentSoap.setPaymentId(payment.getPaymentId());
        paymentSoap.setPaymentDate(dateToXML(payment.getPaymentDate()));
        paymentSoap.setEmail(payment.getEmail());
        paymentSoap.setTicketCost(payment.getTicketCost());
        paymentSoap.setTicketCount(payment.getTicketCount());

        return paymentSoap;
    }

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

        int a = ticketSoaps.size();

        return ticketSoaps;
    }
//
//    public City cityConverter(CitySoap citySoap) {
//        City city = new City();
//
//        city.setCityId(citySoap.getCityId());
//        city.setCityName(citySoap.getCityName());
//
//        return city;
//    }
//


//    public Payment paymentConverter(PaymentSoap paymentSoap) {
//        Payment payment = new Payment();
//
//        payment.setPaymentId(paymentSoap.getPaymentId());
//
//        payment.setPaymentDate(
//                dateConverter(paymentSoap.getPaymentDate())
//        );
//
//        payment.setEmail(paymentSoap.getEmail());
//        payment.setTicketCost(paymentSoap.getTicketCost());
//        payment.setTicketCount(paymentSoap.getTicketCount());
//
//        return payment;
//    }



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



    private LocalDate dateConverter(XMLGregorianCalendar XMLDate) {
        return DatatypeConverter
                .parseDate(XMLDate.toString())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private XMLGregorianCalendar dateToXML(LocalDate localDate) {
        XMLGregorianCalendar calendar = null;

        try {

            calendar = DatatypeFactory
                    .newInstance()
                    .newXMLGregorianCalendar(localDate.toString());

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return calendar;
    }


}
