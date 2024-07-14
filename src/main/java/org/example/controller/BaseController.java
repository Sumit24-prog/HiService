package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BaseController {
    // Global exception handler example
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
        // Log the exception details
        // Return a standardized error response
        return ResponseEntity.internalServerError().body("An error occurred: " + ex.getMessage());
    }
}