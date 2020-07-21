package com.epam.brest2019.courses.consumer;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class PaymentRestConsumer implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public PaymentRestConsumer() {
    }


    public PaymentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Payment> find() {
        LOGGER.debug("Find  payments");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<Payment>) responseEntity.getBody();
    }

    @Override
    public List<Payment> findWitchDirection() {
        LOGGER.debug("Find  ticket with direction");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Payment>) responseEntity.getBody();
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        LOGGER.debug("Find payments by ticketId ({})", ticketId);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/find-By-TicketId", List.class);
        return (List<Payment>) responseEntity.getBody();
    }

    @Override
    public Payment findById(Integer paymentId) {
        LOGGER.debug("Find by paymentId ({})", paymentId);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + paymentId, Payment.class);
        return (Payment) responseEntity.getBody();
    }


    @JmsListener(destination = "consumingChannel")
    @Override
    public void add(Payment payment) {
        LOGGER.info("Add payment from messageChannel({})", payment);
        restTemplate.postForEntity(url, payment, Payment.class);
    }

    @Override
    public void update(Payment payment) {
        LOGGER.debug("Update payment ({})", payment);
        restTemplate.put(url, payment);
    }

    @Override
    public void delete(Integer paymentId) {
        LOGGER.debug("Delete payment ({})", paymentId);
        restTemplate.delete(url + "/" + paymentId);
    }

    @Override
    public List<Payment> searchByDate(LocalDate startDate, LocalDate finishDate) {
        LOGGER.debug("Search paid-tickets by date");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + startDate + "/" + finishDate, List.class);
        return (List<Payment>) responseEntity.getBody();
    }

}
