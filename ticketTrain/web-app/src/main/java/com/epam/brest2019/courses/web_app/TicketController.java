package com.epam.brest2019.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @GetMapping(value = "/tickets")
    public String tickets(Model model){
        return "purchaseTickets";
    }

    @GetMapping(value = "/ticket")
    public String ticket(Model model){
        return "payTickets";
    }
}
