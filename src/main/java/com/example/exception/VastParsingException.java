package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VastParsingException extends RuntimeException {
    public VastParsingException(String message) {
        super(message);
    }

    public VastParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}

