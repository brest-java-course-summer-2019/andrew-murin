package com.epam.brest2019.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping(value = "/payment")
    public String payment(Model model){
        return "payment";
    }
}
