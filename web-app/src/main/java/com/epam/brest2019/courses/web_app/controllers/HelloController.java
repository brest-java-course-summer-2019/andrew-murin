package com.epam.brest2019.courses.web_app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hello MVC Controller
 */

@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    /**
     * Goto index page.
     *
     * @return view name
     */
    @GetMapping("/")
    public String hello(@RequestParam(value= "name", required = false, defaultValue = "World") String name,
                        Model model) {
        LOGGER.debug("Index controller ({})", name);
        model.addAttribute("name", name);
        return "index";
    }

}
