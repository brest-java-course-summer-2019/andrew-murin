package com.epam.brest2019.courses.soap.endpoint;


import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.soap.converter.PaymentConverter;
import com.epam.brest2019.courses.soap.model.payment.*;
import com.epam.brest2019.courses.soap.model.ticket.GetSearchTicketByDateAndDirectionRequest;
import com.epam.brest2019.courses.soap.model.ticket.GetSearchTicketByDateAndDirectionResponse;
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
 * PaymentEndpoint, receives and sends objects in xml
 */
@Endpoint
public class PaymentEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentEndpoint.class);

    private static final String PAYMENT_URI = "http://epam.com/brest2019/courses/soap/model/payment";

    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    @Autowired
    public PaymentEndpoint(PaymentService paymentService, PaymentConverter paymentConverter) {
        LOGGER.debug("Initialize constructor, paymentService- {}, paymentConverter - {} "
                                            , paymentService, paymentConverter);

        this.paymentService = paymentService;
        this.paymentConverter = paymentConverter;
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getAllPaymentRequest")
    @ResponsePayload
    public GetAllPaymentResponse getAllTicketResponse(@RequestPayload GetAllPaymentRequest request) {
        LOGGER.debug("GetAllPaymentRequest - {}", request);
        GetAllPaymentResponse response = new GetAllPaymentResponse();

        response.getListOfPayment().addAll(paymentConverter.paymentsConvertToSoapList(paymentService.findAll()));

        return response;
    }


    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getPaymentByIdRequest")
    @ResponsePayload
    public GetPaymentByIdResponse getPaymentByIdResponse(@RequestPayload GetPaymentByIdRequest request) {
        LOGGER.debug("GetPaymentByIdRequest - {}", request);

        GetPaymentByIdResponse response = new GetPaymentByIdResponse();

        response.setPayment(
                paymentConverter.paymentConverterToSoap(
                        paymentService.findById(request.getPaymentId())));

        return response;
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getAddPaymentRequest")
    public void getAddPaymentResponse(@RequestPayload GetAddPaymentRequest request) {
        LOGGER.debug("GetAddPaymentRequest - {}", request);
        paymentService.add(paymentConverter.paymentConverterSoapToPayment(request.getPayment(), ADD));
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getUpdatePaymentRequest")
    public void getUpdatePaymentResponse(@RequestPayload GetUpdatePaymentRequest request) {
        LOGGER.debug("GetUpdatePaymentRequest - {}", request);
        paymentService.update(paymentConverter.paymentConverterSoapToPayment(request.getPayment(), UPDATE));
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getDeletePaymentRequest")
    public void getDeletePaymentResponse(@RequestPayload GetDeletePaymentRequest request) {
        LOGGER.debug("GetDeletePaymentRequest - {}", request);
        paymentService.delete(request.getPaymentId());
    }

    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getSearchByDateRequest")
    @ResponsePayload
    public GetSearchByDateResponse getSearchByDateResponse(
            @RequestPayload GetSearchByDateRequest request) {
        LOGGER.debug("GetSearchByDateRequest - {}", request);

        GetSearchByDateResponse response = new GetSearchByDateResponse();

        try {
            LocalDate startDate = dateConverter(request.getStartDate());
            LocalDate finishDate = dateConverter(request.getFinishDate());

            List<Payment> paymentList = paymentService.searchByDate(startDate, finishDate);

            response.getListOfPayment().addAll(paymentConverter.paymentsConvertToSoapList(paymentList));
        } catch (Exception ex) {
            LOGGER.debug("Exception ex: {}", ex.getMessage());
        }

        return response;
    }


    @PayloadRoot(namespace = PAYMENT_URI, localPart = "getFindByTicketIdRequest")
    @ResponsePayload
    public GetFindByTicketIdResponse getFindByTicketIdResponse(@RequestPayload GetFindByTicketIdRequest request) {
        LOGGER.debug("GetFindByTicketIdRequest - {}", request);

        GetFindByTicketIdResponse response = new GetFindByTicketIdResponse();

        response.getListOfPayment().addAll(
                paymentConverter.paymentsConvertToSoapList(
                        paymentService.findByTicketId(request.getTicketId())));

        return response;
    }


}
