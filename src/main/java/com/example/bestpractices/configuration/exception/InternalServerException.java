package com.example.bestpractices.configuration.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends RestException {

    private static final long serialVersionUID = 1L;

	public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
