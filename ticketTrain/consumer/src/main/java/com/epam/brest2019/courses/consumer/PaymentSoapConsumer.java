package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.soap.converter.PaymentConverter;
import com.epam.brest2019.courses.model.soap.model.payment.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.time.LocalDate;

import static com.epam.brest2019.courses.model.soap.converter.Converter.*;

@Component
public class PaymentSoapConsumer extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentSoapConsumer.class);

    private static final String PAYMENT_URI = "http://localhost:8088/ws/payment";

    @Autowired
    private PaymentConverter paymentConverter;


    public GetAllPaymentResponse findAll() {
        GetAllPaymentRequest request = new GetAllPaymentRequest();
        LOGGER.debug("Find all payments - {}", request);

        return (GetAllPaymentResponse) getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }


    public GetAllWithDirectionResponse findAllPaymentWithDirection() {
        GetAllWithDirectionRequest request = new GetAllWithDirectionRequest();
        LOGGER.debug("Find all payments with direction - {}", request);

        return (GetAllWithDirectionResponse) getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }


    public GetSearchByDateResponse searchPaymentByDate(LocalDate startDate, LocalDate finishDate) {
        GetSearchByDateRequest request = new GetSearchByDateRequest();
        request.setStartDate(dateToXML(startDate));
        request.setFinishDate(dateToXML(finishDate));
        LOGGER.debug("Search payments by date - {}", request);

        return (GetSearchByDateResponse) getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }


    public GetPaymentByIdResponse findPaymentById(int paymentId) {
        GetPaymentByIdRequest request = new GetPaymentByIdRequest();
        request.setPaymentId(paymentId);
        LOGGER.debug("Find payment by id - {}", request);

        return (GetPaymentByIdResponse) getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }

    @JmsListener(destination = "sendToQueue")
    public void addPayment(Payment payment) {
        GetAddPaymentRequest request = new GetAddPaymentRequest();
        request.setPayment(paymentConverter.paymentConverterToSoap(payment, ADD));
        LOGGER.debug("Add payment - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }

    public void updatePayment(Payment payment) {
        GetUpdatePaymentRequest request = new GetUpdatePaymentRequest();
        request.setPayment(paymentConverter.paymentConverterToSoap(payment, UPDATE));
        LOGGER.debug("Add payment - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }


    public void deletePayment(int paymentId) {
        GetDeletePaymentRequest request = new GetDeletePaymentRequest();
        request.setPaymentId(paymentId);
        LOGGER.debug("Delete payment - {}", request);

        getWebServiceTemplate().marshalSendAndReceive(PAYMENT_URI, request);
    }




}
