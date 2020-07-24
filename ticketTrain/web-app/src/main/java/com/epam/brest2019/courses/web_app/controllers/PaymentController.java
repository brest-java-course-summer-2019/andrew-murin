package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.consumer.PaymentSoapConsumer;
import com.epam.brest2019.courses.consumer.TicketSoapConsumer;
import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.model.soap.converter.PaymentConverter;
import com.epam.brest2019.courses.model.soap.converter.TicketConverter;
import com.epam.brest2019.courses.web_app.validators.PaymentValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
import java.util.List;

import static com.epam.brest2019.courses.model.soap.converter.Converter.UPDATE;

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
    private PaymentConverter paymentConverter;
    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private PaymentSoapConsumer paymentSoapConsumer;

    @Autowired
    private TicketSoapConsumer ticketSoapConsumer;

    @Autowired
    private PaymentValidator paymentValidator;

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Goto paid-tickets page.
     *
     * @param model model
     * @return paid-tickets
     */
    @GetMapping("/paid-tickets")
    public final String paidTickets(Model model) throws JsonProcessingException {
        LOGGER.debug("Find all paid tickets");
        List<Payment> payments = paymentConverter.paymentsConvertListSoapToList(
                paymentSoapConsumer.findAllPaymentWithDirection().getListOfPayment());


        ObjectMapper mapper = new ObjectMapper();
        List<Payment> paymentList = mapper.convertValue(payments,
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
//

        model.addAttribute("isNotSearch", true);
        model.addAttribute("payments", payments);
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

        Payment payment = paymentConverter.paymentConverterSoapToPayment(
                paymentSoapConsumer.findPaymentById(id).getPayment(), UPDATE);

        List<Ticket> tickets = ticketConverter.ticketListSoapToList(ticketSoapConsumer.findAll().getListOfTicket());

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
                                         @PathVariable("id") int id,
                                         @Valid Payment payment, BindingResult result) {
        LOGGER.debug("Update paid-ticket ({}, {}, {})", paidTicketDate, payment, result);

        Ticket ticket = ticketConverter.ticketSoapConverterToTicket(
                ticketSoapConsumer.findTicketById(id).getTicket(), UPDATE);


        payment.setPaymentDate(LocalDate.parse(paidTicketDate));
        payment.setTicketId(ticket);
        payment.setEmail("12345@mail.ru");

        LOGGER.debug("Payment {}", payment);
        paymentValidator.validate(payment, result);


        if(result.hasErrors()){
            return "redirect:/paid-ticket/" + payment.getPaymentId();

        } else {
            paymentSoapConsumer.updatePayment(payment);
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

        paymentSoapConsumer.deletePayment(id);

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

        List<Payment> payments = paymentConverter.paymentsConvertListSoapToList(
                paymentSoapConsumer.searchPaymentByDate(startDateView, finishDateView).getListOfPayment());

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

        Ticket ticket = ticketConverter.ticketSoapConverterToTicket(
                ticketSoapConsumer.findTicketById(id).getTicket(), UPDATE);

        Payment payment = new Payment();
        ticket.setTicketId(id);

        payment.setPaymentDate(LocalDate.now());
        payment.setTicketId(ticket);
        payment.setEmail(email);
//        paymentSoapConsumer.addPayment(payment);
        jmsTemplate.convertAndSend("sendToQueue", payment);
        LOGGER.info("LOGGER MESSAGE: {}", payment);

        return "redirect:/tickets";
    }



}
