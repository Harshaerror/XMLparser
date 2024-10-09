package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Log the exception (optional)
        ex.printStackTrace();
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VastParsingException.class) // Custom exception
    public ResponseEntity<String> handleVastParsingException(VastParsingException ex) {
        // Log the specific parsing exception (optional)
        return new ResponseEntity<>("VAST Parsing Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
