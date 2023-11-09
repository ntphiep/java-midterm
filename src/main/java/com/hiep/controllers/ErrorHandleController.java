package com.hiep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;



@Controller
public class ErrorHandleController {


    @GetMapping("/error")
    public String getErrorHandle(HttpServletRequest request) {
        System.out.println("error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        // System.out.println(status);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
                return "404";

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
                return "404";

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "404";
            }
        }
        return "404";
    }
}
