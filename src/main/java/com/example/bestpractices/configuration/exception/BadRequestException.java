package com.example.bestpractices.configuration.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "Bad Request");
    }

}
