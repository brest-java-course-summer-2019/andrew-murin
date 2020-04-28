package com.epam.brest2019.courses.soap.converter;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.soap.model.city.CitySoap;
import com.epam.brest2019.courses.soap.model.payment.PaymentSoap;
import com.epam.brest2019.courses.soap.model.ticket.TicketSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.soap.converter.Converter.*;

@Component
public class PaymentConverter {

    @Autowired
    private TicketConverter ticketConverter;

    public PaymentSoap paymentConverterToSoap(Payment payment) {
        PaymentSoap paymentSoap = new PaymentSoap();

        paymentSoap.setPaymentId(payment.getPaymentId());
        paymentSoap.setPaymentDate(dateToXML(payment.getPaymentDate()));
        paymentSoap.setEmail(payment.getEmail());
        paymentSoap.setTicketCost(payment.getTicketCost());
        paymentSoap.setTicketCount(payment.getTicketCount());

        return paymentSoap;
    }

    public Payment paymentConverterSoapToPayment(PaymentSoap paymentSoap, String add) {
        Payment payment = new Payment();
        Ticket ticket;

        ticket = ticketConverter.ticketSoapConverterToTicket(paymentSoap.getTicketId(), UPDATE);

        //Payment
        if (!add.equals("true")) {
            payment.setPaymentId(paymentSoap.getPaymentId());
        }

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

            paymentSoap.setPaymentId(payments.get(i).getPaymentId());
            paymentSoap.setPaymentDate(dateToXML(payments.get(i).getPaymentDate()));
            paymentSoap.setTicketCost(payments.get(i).getTicketCost());
            paymentSoap.setTicketCount(payments.get(i).getTicketCount());
            paymentSoap.setEmail(payments.get(i).getEmail());
            paymentSoap.setTicketId(ticketConverter.ticketConverterToSoap(payments.get(i).getTicketId()));
            
            paymentSoaps.add(paymentSoap);
        }

        return paymentSoaps;
    }

}
