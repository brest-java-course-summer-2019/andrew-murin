package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentRestController {
    private final static Logger logger = LoggerFactory.getLogger(PaymentRestController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        logger.debug("Get all payments");
        return paymentService.findAll();
    }

    @GetMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment findPaymentById(@PathVariable Integer id) {
        logger.debug("find payment by id");
        return paymentService.findById(id);
    }

    @PutMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePayment(@PathVariable Integer id, @RequestBody Payment payment) {
        logger.debug("update payment ({})", payment);
        paymentService.update(payment);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable Integer id) {
        logger.debug("delete payment by id");
        paymentService.delete(id);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        logger.debug("add payment ({})", payment);
        Payment result = paymentService.add(payment);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
