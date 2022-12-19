package com.example.bestpractices.configuration.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
