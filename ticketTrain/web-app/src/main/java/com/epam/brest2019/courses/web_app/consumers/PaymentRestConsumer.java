package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PaymentRestConsumer implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public PaymentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Payment> findAll() {
        LOGGER.debug("finAll()");
        ResponseEntity responseEntity1 = restTemplate.getForEntity(url + "/", List.class);
        return (List<Payment>) responseEntity1.getBody();
    }

    @Override
    public List<Payment> findByTicketId(Integer ticketId) {
        LOGGER.debug("Find by ticket id ({})", ticketId);
        ResponseEntity<Payment> responseEntity2 = restTemplate.getForEntity(url + "/" + ticketId, Payment.class);
        return (List<Payment>) responseEntity2.getBody();
    }

    @Override
    public Payment findById(Integer paymentId) {
        LOGGER.debug("Find by id ({})", paymentId);
        ResponseEntity<Payment> responseEntity3 = restTemplate.getForEntity(url + "/" +paymentId, Payment.class);
        return responseEntity3.getBody();
    }

    @Override
    public Payment add(Payment payment) {
        LOGGER.debug("Add payment ({})", payment);
        ResponseEntity responseEntity3 = restTemplate.postForEntity(url,payment, Payment.class);
        return (Payment) responseEntity3.getBody();
    }

    @Override
    public void update(Payment payment) {
        LOGGER.debug("Update payment ({})", payment);
        restTemplate.put(url, payment);
    }

    @Override
    public void delete(Integer paymentId) {
        LOGGER.debug("delete payment by id ({})", paymentId);
        restTemplate.delete(url + "/", paymentId);
    }
}
