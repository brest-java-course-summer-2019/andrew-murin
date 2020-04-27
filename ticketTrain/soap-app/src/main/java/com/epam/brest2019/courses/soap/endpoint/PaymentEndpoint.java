package com.epam.brest2019.courses.soap.endpoint;


import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.soap.converter.PaymentConverter;
import com.epam.brest2019.courses.soap.model.payment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class PaymentEndpoint {

    private static final String PAYMENT_URI = "http://epam.com/brest2019/courses/soap/model/payment";

    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    @Autowired
    public PaymentEndpoint(PaymentService paymentService, PaymentConverter paymentConverter) {
        this.paymentService = paymentService;
        this.paymentConverter = paymentConverter;
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getAllPaymentRequest")
    @ResponsePayload
    public GetAllPaymentResponse getAllTicketResponse(@RequestPayload GetAllPaymentRequest request) {
        GetAllPaymentResponse response = new GetAllPaymentResponse();

        response.getListOfPayment().addAll(paymentConverter.paymentsConvertToSoapList(paymentService.findAll()));

        return response;
    }


    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getPaymentByIdRequest")
    @ResponsePayload
    public GetPaymentByIdResponse getPaymentByIdResponse(@RequestPayload GetPaymentByIdRequest request) {

        GetPaymentByIdResponse response = new GetPaymentByIdResponse();

        response.setPayment(
                paymentConverter.paymentConverterToSoap(
                        paymentService.findById(request.getPaymentId())));

        return response;
    }
//
////    TODO: get error ("LogicalConnectionManagedImpl from hibernate")
////    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getAddTicketRequest")
////    @ResponsePayload
////    public void getAddTicketResponse(@RequestPayload GetAddTicketRequest request) {
////        ticketService.add(converter.ticketSoapConverterToTicket(request.getTicket()));
////    }
//
    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getDeletePaymentRequest")
    @ResponsePayload
    public void getDeletePaymentResponse(@RequestPayload GetDeletePaymentRequest request) {
        paymentService.delete(request.getPaymentId());
    }

}
