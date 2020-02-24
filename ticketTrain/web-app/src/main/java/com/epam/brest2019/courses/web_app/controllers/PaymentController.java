package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.web_app.config.SenderConfig;
import com.epam.brest2019.courses.web_app.validators.PaymentValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Payment controller
 */
@Controller
public class PaymentController{

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentValidator paymentValidator;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ApplicationContext context;


    /**
     * Goto paid-tickets page.
     *
     * @param model model
     * @return paid-tickets
     */
    @GetMapping("/paid-tickets")
    public final String paidTickets(Model model) throws JsonProcessingException {
        LOGGER.debug("Find all paid tickets");
        List<Payment> payments = paymentService.findAllWitchDirection();


        ObjectMapper mapper = new ObjectMapper();
        List<Payment> paymentList = mapper.convertValue(paymentService.findAllWitchDirection(),
                new TypeReference<List<Payment>>(){
                }
        );



        int totalCountTicket = (int) paymentList.stream()
                .filter(payment -> payment.getTicketCount() != null)
                .mapToLong(Payment::getTicketCount).sum();


        BigDecimal summ = paymentList.stream()
                .filter(payment -> payment.getTicketCost() != null)
                .map(Payment::getTicketCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        model.addAttribute("isNotSearch", true);
        model.addAttribute("payments", paymentService.findAllWitchDirection());
        model.addAttribute("countTicket", totalCountTicket);
        model.addAttribute("totalSum", summ);

        return "paid-tickets";
    }

    /**
     * Goto edit page for paid-ticket
     * @param id
     * @param model
     * @return paid-tickets
     */
    @GetMapping("/paid-ticket/{id}")
    public final String gotoEditPaidTicketPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("goto edit paid-ticket page({})", id);

        Payment payment = paymentService.findById(id);
        List<Ticket> tickets = ticketService.findAll();

        model.addAttribute("paidTicket", payment);
        model.addAttribute("tickets", tickets);

        return "paid-ticket";
    }

    /**
     * Make update of paid-ticket
     * @param payment
     * @return paid-tickets
     */
    @PostMapping("/paid-ticket/{id}")
    public final String updatePaidTicket(@ModelAttribute("paidTicketDate") String paidTicketDate,
                                         @Valid Payment payment, BindingResult result) {
        LOGGER.debug("Update paid-ticket ({}, {}, {})", paidTicketDate, payment, result);


        payment.setPaymentDate(LocalDate.parse(paidTicketDate));
        paymentValidator.validate(payment, result);

        if(result.hasErrors()){
            return "redirect:/paid-ticket/" + payment.getPaymentId();

        } else {
            this.paymentService.update(payment);
            return "redirect:/paid-tickets";
        }

    }

    /**
     * Delete paid-ticket
     * @param id
     * @return paid-tickets
     */
    @GetMapping("/paid-ticket/{id}/delete")
    public final String deletePayment(@PathVariable Integer id) {
        LOGGER.debug("Delete paid-ticket({})", id);

        Payment payment = paymentService.findById(id);

        this.paymentService.delete(id);

        return "redirect:/paid-tickets";
    }

    /**
     * Search paid-tickets by date
     * @param startDate
     * @param finishDate
     * @param model
     * @return paid-tickets
     */
    @PostMapping("/paid-tickets")
    public final String searchByDate(@ModelAttribute("startDate") String startDate,
                                     @ModelAttribute("finishDate") String finishDate,
                                     Model model) {

        LOGGER.debug("Search paid-tickets by date ({} - {})", startDate, finishDate);

        LocalDate startDateView;
        LocalDate finishDateView;

        try {
            startDateView = LocalDate.parse(startDate);
            finishDateView = LocalDate.parse(finishDate);

        } catch (Exception ex) {
            startDateView = LocalDate.of(2019,1,1);
            finishDateView = LocalDate.now();
        }

        List<Payment> payments = paymentService.searchByDate(startDateView, finishDateView);

        model.addAttribute("isSearch", true);
        model.addAttribute("paymentsSearched", payments);

        return "paid-tickets";
    }


    /**
     * Pay of ticket
     * @param id
     * @return tickets
     */
    @PostMapping(value = "/pay-ticket/{id}", produces = "text/html")
    public final String payTicket(@PathVariable Integer id,
                                  @ModelAttribute("email") String email) throws InterruptedException {
        LOGGER.debug("Pay ticket({}, {})", id, email);

        Ticket ticket = new Ticket();
        Payment payment = new Payment();
        ticket.setTicketId(id);

        payment.setPaymentDate(LocalDate.now());
        payment.setTicketId(ticket);
        payment.setEmail(email);

        SenderConfig.Sender sender = context.getBean(SenderConfig.Sender.class);
        sender.send(payment);
        LOGGER.info("LOGGER MESSAGE: {}, {}", sender, payment);

        return "redirect:/tickets";
    }



}
