package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.web_app.validators.PaymentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentValidator paymentValidator;

    @GetMapping("/paid-tickets")
    public final String paidTickets(Model model) {
        LOGGER.debug("Get all payments: ({})", model);
        model.addAttribute("paid-tickets", paymentService.findAll());
        return "paid-tickets";
    }

    @GetMapping("/paid-ticket/{id}")
    public final String gotoEditDepartmentPage(@PathVariable Integer id, Model model){
        LOGGER.debug("Get payment: ({}, {})", id, model);

        Payment payment = paymentService.findById(id);
        model.addAttribute("paid-ticket", payment);
        return "paid-ticket";
    }

    @PostMapping("/paid-ticket/{id}")
    public final String updatePayment(@Valid Payment payment, BindingResult bindingResult) {
        LOGGER.debug("Go to add payment page ({}, {})",payment, bindingResult);
        paymentValidator.validate(payment, bindingResult);
        if (bindingResult.hasErrors()) {
            return "paid-ticket";
        } else {
            this.paymentService.update(payment);
            return "redirect:/paid-tickets";
        }
    }

    @GetMapping("/paid-ticket")
    public final String gotoAddPaymentPage(Model model) {
        LOGGER.debug("gotoAddPaymentPage:({})", model);
        Payment payment = new Payment();
        model.addAttribute("isNew", true);
        model.addAttribute("paid-ticket", payment);
        return "paid-ticket";
    }

    @PostMapping()
    public final String addPayment(@Valid Payment payment, BindingResult bindingResult) {
        LOGGER.debug("addPaymment ({}, {})", payment, bindingResult);
        paymentValidator.validate(payment, bindingResult);
        if(bindingResult.hasErrors()){
            return "paid-ticket";
        } else {
            this.paymentService.add(payment);
            return "redirect:/paid-tickets";
        }
    }

    @GetMapping()
    public final String deletePaymentById(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete Paymentby id ({}, {})", id, model);
        paymentService.delete(id);
        return "redirect:/paid-tickets";
    }
































}
