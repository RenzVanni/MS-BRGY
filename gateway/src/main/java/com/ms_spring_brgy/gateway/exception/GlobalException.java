package com.ms_spring_brgy.gateway.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String handleNullPointerException(Exception ex) {
        return "Error: " + ex.getMessage();
    }
}
