package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PaymentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRestController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<Payment> findAll() {
        LOGGER.debug("Find all payments");
        return paymentService.findAll();
    }

    @GetMapping("/payments/find-All-With-Direction")
    public List<Payment> findAllWitchDirection() {
        LOGGER.debug("Find all payments with direction");
        return paymentService.findAllWitchDirection();
    }


    @GetMapping("/payments/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Payment> findByTicketId(@PathVariable("ticketId") Integer ticketId) {
        LOGGER.debug("Find payments by ticketId ({})", ticketId);
        List<Payment> payments = paymentService.findByTicketId(ticketId);
        return payments;
    }

    @GetMapping("/payments/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public Payment findById(@PathVariable("paymentId") Integer paymentId) {
        LOGGER.debug("Find by paymentId ({})", paymentId);
        return paymentService.findById(paymentId);
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        LOGGER.debug("Add payment ({})", payment);
        Payment result = paymentService.add(payment);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/payments")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePayment(@RequestBody Payment payment) {
        LOGGER.debug("Update payment ({})", payment);
        paymentService.update(payment);
    }

    @DeleteMapping("/payments/{paymentId}")
    public void deletePayment(@PathVariable("paymentId") Integer paymentId) {
        LOGGER.debug("Delete payment by paymentId ({})", paymentId);
        paymentService.delete(paymentId);
    }

    @GetMapping("/payments/{startDate}/{finishDate}")
    public List<Payment> searchByDate(@PathVariable("startDate") String startDate,
                                      @PathVariable("finishDate") String finishDate) {

        LOGGER.debug("Search by date ({} - {})", startDate, finishDate);
        LocalDate startDateView = LocalDate.parse(startDate);
        LocalDate finishDateView = LocalDate.parse(finishDate);

        return paymentService.searchByDate(startDateView, finishDateView);
    }






}
