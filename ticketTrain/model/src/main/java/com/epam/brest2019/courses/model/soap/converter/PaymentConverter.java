package com.epam.brest2019.courses.model.soap.converter;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.model.payment.PaymentSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.brest2019.courses.model.soap.converter.Converter.*;

@Component
public class PaymentConverter {

    @Autowired
    private TicketConverter ticketConverter;

    public PaymentSoap paymentConverterToSoap(Payment payment, String add) {
        PaymentSoap paymentSoap = new PaymentSoap();

        if (!add.equals("true")) {
            paymentSoap.setPaymentId(payment.getPaymentId());
        }
        paymentSoap.setPaymentDate(dateToXML(payment.getPaymentDate()));
        paymentSoap.setEmail(payment.getEmail());
        paymentSoap.setTicketCost(payment.getTicketCost());
        paymentSoap.setTicketCount(payment.getTicketCount());
        paymentSoap.setTicketId(ticketConverter.ticketConverterToSoap(payment.getTicketId(), UPDATE));

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
            paymentSoap.setEmail(payments.get(i).getEmail());

            paymentSoap.setTicketCost(payments.get(i).getTicketCost());
            paymentSoap.setTicketCount(payments.get(i).getTicketCount());
            paymentSoap.setTicketId(ticketConverter.ticketConverterToSoap(payments.get(i).getTicketId(), UPDATE));
            
            paymentSoaps.add(paymentSoap);
        }

        return paymentSoaps;
    }

    /**
     * Need fake-values as paymentId, paymentDate, email because XML not sending null-values
     * @param payments
     * @return
     */
    public List<PaymentSoap> paymentsConvertFindAllWithDirection(List<Payment> payments) {
        List<PaymentSoap> paymentSoaps = new ArrayList<>();

        for (int i = 0; i < payments.size(); i++) {
            PaymentSoap paymentSoap = new PaymentSoap();

            paymentSoap.setPaymentId(i);
            paymentSoap.setPaymentDate(dateToXML(LocalDate.now()));
            paymentSoap.setEmail("piska123@mail.ru");

            paymentSoap.setTicketCost(payments.get(i).getTicketCost());
            paymentSoap.setTicketCount(payments.get(i).getTicketCount());
            paymentSoap.setTicketId(ticketConverter.ticketConverterToSoap(payments.get(i).getTicketId(), UPDATE));

            paymentSoaps.add(paymentSoap);
        }

        return paymentSoaps;
    }

    public List<Payment> paymentsConvertListSoapToList(List<PaymentSoap> paymentSoaps) {
        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < paymentSoaps.size(); i++) {
            Payment payment = new Payment();


            payment.setPaymentId(paymentSoaps.get(i).getPaymentId());
            payment.setPaymentDate(dateConverter(paymentSoaps.get(i).getPaymentDate()));
            payment.setEmail(paymentSoaps.get(i).getEmail());

            payment.setTicketCost(paymentSoaps.get(i).getTicketCost());
            payment.setTicketCount(paymentSoaps.get(i).getTicketCount());
            payment.setTicketId(ticketConverter.ticketSoapConverterToTicket(paymentSoaps.get(i).getTicketId(), UPDATE));

            paymentList.add(payment);
        }

        return paymentList;
    }

}
