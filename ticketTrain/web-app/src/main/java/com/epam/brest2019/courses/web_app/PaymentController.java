package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public final String payments(Model model) {
        LOGGER.debug("Get all payments: ({})", model);
        model.addAttribute("payments", paymentService.findAll());
        return "payments";
    }

    @GetMapping("tickets/{id}")
    public final String payment(@PathVariable Integer id, Model model){
        LOGGER.debug("Get payment: ({}, {})", id, model);

        Payment payment = paymentService.findById(id);
        model.addAttribute("payment", payment);
        return "payment";
    }



}
