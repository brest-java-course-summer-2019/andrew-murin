package com.epam.brest2019.courses.web_app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**
     *Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * Default page
     * @return index-view
     */
    @GetMapping("/index")
    public String index() {
        LOGGER.debug("Index controller");
        return "index";
    }
}
