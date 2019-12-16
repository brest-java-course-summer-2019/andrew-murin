package com.epam.brest2019.courses.web_app.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger
     */
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Receive all errors and handle them
     *
     * @return exception-view
     */
    @ExceptionHandler()
    public String handlerExcpetion() {
        LOGGER.debug("Servers error");
        return "exception";
    }
}
