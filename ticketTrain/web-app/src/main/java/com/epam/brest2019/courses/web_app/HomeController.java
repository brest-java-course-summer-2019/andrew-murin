package com.epam.brest2019.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Hello MVC controller
 */

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public String hello(Model model){
        return "index";
    }
}
