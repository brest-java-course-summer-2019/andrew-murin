package com.epam.brest2019.courses.web_app.controllers;

import com.epam.brest2019.courses.model.Payment;
import com.epam.brest2019.courses.model.Ticket;
import com.epam.brest2019.courses.service.PaymentService;
import com.epam.brest2019.courses.service.TicketService;
import com.epam.brest2019.courses.web_app.validators.PaymentValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Payment controller
 */
@Controller
public class PaymentController {

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
        List<Payment> paymentList = mapper.convertValue(payments,
                new TypeReference<List<Payment>>(){
                    }
        );

        int totolCountTicket = paymentList.stream()
                .filter(payment -> payment.getTicketCount() != null)
                .mapToInt(Payment::getTicketCount).sum();

        BigDecimal summ = paymentList.stream()
                .filter(payment -> payment.getTotalCost() != null)
                .map(Payment::getTotalCost)
                .reduce(BigDecimal::add).get();



        model.addAttribute("isNotSearch", true);
        model.addAttribute("payments", payments);
        model.addAttribute("countTicket", totolCountTicket);
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
            finishDateView = LocalDate.of(2019,12,12);
        }

        List<Payment> payments = paymentService.searchByDate(startDateView, finishDateView);

        model.addAttribute("isSearch", true);
        model.addAttribute("payments", payments);
//        model.addAttribute("isSearch", false);
        return "paid-tickets";
    }

    /**
     * Pay of ticket
     * @param id
     * @param payment
     * @return tickets
     */
    @GetMapping("/pay-ticket/{id}")
    public final String payTicket(@PathVariable Integer id,
                                  @Valid Payment payment, BindingResult result) {
        LOGGER.debug("Pay ticket({}, {})",id, payment);

        payment.setPaymentDate(LocalDate.now());
        payment.setTicketId(id);

        if (result.hasErrors()){
            return "redirect:/pay-ticket/" + id;
        } else {
            paymentService.add(payment);
            return "redirect:/tickets";
        }

    }

}
