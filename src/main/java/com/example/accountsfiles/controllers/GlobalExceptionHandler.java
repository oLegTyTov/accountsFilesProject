package com.example.accountsfiles.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {



    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Model model, Exception exception) {
        return "html/error"; // Назва HTML шаблону без розширення
    }
}
