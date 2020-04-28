package com.epam.brest2019.courses.soap.converter;

import com.epam.brest2019.courses.model.City;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.soap.model.city.CitySoap;
import com.epam.brest2019.courses.soap.model.payment.PaymentSoap;
import com.epam.brest2019.courses.soap.model.ticket.TicketSoap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.soap.converter.Converter.dateConverter;
import static com.epam.brest2019.courses.soap.converter.Converter.dateToXML;

@Component
public class PaymentConverter {

    public PaymentSoap paymentConverterToSoap(Payment payment) {
        PaymentSoap paymentSoap = new PaymentSoap();

        paymentSoap.setPaymentId(payment.getPaymentId());
        paymentSoap.setPaymentDate(dateToXML(payment.getPaymentDate()));
        paymentSoap.setEmail(payment.getEmail());
        paymentSoap.setTicketCost(payment.getTicketCost());
        paymentSoap.setTicketCount(payment.getTicketCount());

        return paymentSoap;
    }

    public Payment paymentConverterSoapToPayment(PaymentSoap paymentSoap) {
        Payment payment = new Payment();
        Ticket ticket = new Ticket();
        City cityFrom = new City();
        City cityTo = new City();

        //City
        CitySoap citySoapFrom = paymentSoap.getTicketId().getFromCity();
        CitySoap citySoapTo = paymentSoap.getTicketId().getToCity();

        cityFrom.setCityId(citySoapFrom.getCityId());
        cityFrom.setCityName(citySoapFrom.getCityName());

        cityTo.setCityId(citySoapTo.getCityId());
        cityTo.setCityName(citySoapTo.getCityName());

        //Ticket
        ticket.setTicketId(paymentSoap.getTicketId().getTicketId());
        ticket.setTicketCost(paymentSoap.getTicketId().getTicketCost());
        ticket.setFromCity(cityFrom);
        ticket.setToCity(cityTo);
        ticket.setTicketDate(dateConverter(paymentSoap.getTicketId().getTicketDate()));

        //Payment
        payment.setPaymentId(paymentSoap.getPaymentId());
        payment.setTicketId(ticket);
        payment.setPaymentDate(dateConverter(paymentSoap.getPaymentDate()));
        payment.setEmail(paymentSoap.getEmail());
        payment.setTicketCost(paymentSoap.getTicketCost());
        payment.setTicketCount(paymentSoap.getTicketCount());

        return payment;
    }

    public List<PaymentSoap> paymentsConvertToSoapList(List<Payment> payments) {
        List<PaymentSoap> paymentSoaps = new ArrayList<>();

        for (int i = 0; i < payments.size(); i++) {
            PaymentSoap paymentSoap = new PaymentSoap();
            TicketSoap ticketSoap = new TicketSoap();
            CitySoap citySoapFrom = new CitySoap();
            CitySoap citySoapTo = new CitySoap();

            //City
            citySoapFrom.setCityId(payments.get(i).getTicketId().getFromCity().getCityId());
            citySoapFrom.setCityName(payments.get(i).getTicketId().getFromCity().getCityName());
            citySoapTo.setCityId(payments.get(i).getTicketId().getToCity().getCityId());
            citySoapTo.setCityName(payments.get(i).getTicketId().getToCity().getCityName());

            //Ticket
            ticketSoap.setTicketId(payments.get(i).getTicketId().getTicketId());
            ticketSoap.setTicketDate(dateToXML(payments.get(i).getTicketId().getTicketDate()));
            ticketSoap.setTicketCost(payments.get(i).getTicketId().getTicketCost());
            ticketSoap.setFromCity(citySoapFrom);
            ticketSoap.setToCity(citySoapTo);

            //Payment
            paymentSoap.setPaymentId(payments.get(i).getPaymentId());
            paymentSoap.setPaymentDate(dateToXML(payments.get(i).getPaymentDate()));
            paymentSoap.setTicketCost(payments.get(i).getTicketCost());
            paymentSoap.setTicketCount(payments.get(i).getTicketCount());
            paymentSoap.setEmail(payments.get(i).getEmail());
            paymentSoap.setTicketId(ticketSoap);
            
            paymentSoaps.add(paymentSoap);
        }

        return paymentSoaps;
    }

}
