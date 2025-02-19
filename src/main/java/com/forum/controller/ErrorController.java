package com.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "An error occurred";
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            
            switch(statusCode) {
                case 404:
                    errorMessage = "Page not found";
                    break;
                case 500:
                    errorMessage = "Internal server error";
                    break;
            }
        }
        
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}