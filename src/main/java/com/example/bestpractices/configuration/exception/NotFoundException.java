package com.example.bestpractices.configuration.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Not Found");
    }

}
