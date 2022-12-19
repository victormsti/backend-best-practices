package com.example.bestpractices.configuration.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RestException {

    private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

}
