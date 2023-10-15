package com.hcmute.management.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@Slf4j
public class ErrorController {

//    @GetMapping("/error")
    @ExceptionHandler(Exception.class)
    public String getErrorPage(Exception e, HttpServletRequest request, Model model) {
        String errorPage = "error/error";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorPage = "error/404";
            }
        }

        return errorPage;
    }
}
