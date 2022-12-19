package com.example.bestpractices.configuration.exception.handler;

import com.example.bestpractices.configuration.exception.RestException;
import com.example.bestpractices.configuration.exception.UnauthorizedException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.QueryException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/***
 * Global Error Handler
 */
@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorMessage> handleRestException(Exception ex,
                                                            WebRequest request) {
        RestException restException = (RestException) ex;
        ErrorMessage error = new ErrorMessage(restException);

        int status = restException.getStatus().value();


        error = new ErrorMessage(status,
                restException.getMessage());

        return new ResponseEntity<>(error, restException.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorMessage> handleIntegrityViolation(Exception ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Unprocessable Entity");
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ValidationMessage> handlePropertyReferenceException(PropertyReferenceException ex) {
        return new ResponseEntity(String.format("Property %s not found", ex.getPropertyName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryException.class)
    public ResponseEntity<ValidationMessage> handleQueryException(QueryException ex) {
        try {
            int start = ex.getMessage().indexOf(": ") + 2;
            int end = ex.getMessage().indexOf(" of: ");
            String field = ex.getMessage().substring(start, end);

            if (field != null && !field.isEmpty()) {
                return new ResponseEntity(String.format("Property %s not found", field), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(String.format("Sql error: %s", ex.getQueryString()), HttpStatus.BAD_REQUEST);
            }

        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity(String.format("Sql error: %s", ex.getQueryString()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ValidationMessage validationMessage = new ValidationMessage(ex);
        validationMessage.fields.forEach(f->{
            errors.add(f.message);
        });

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ValidationMessage> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ValidationMessage> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ValidationMessage> handleFeignException(FeignException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.valueOf(ex.status()));
    }

    @Data
    @NoArgsConstructor
    private static class ErrorMessage {
        private int status;
        private String message;

        public ErrorMessage(int status, String message) {
            this.status = status;
            this.message = message;
        }

        ErrorMessage(RestException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    private static class ValidationMessage extends ErrorMessage {
        private List<FieldMessage> fields;

        ValidationMessage(MethodArgumentNotValidException ex) {
            this.setStatus(HttpStatus.BAD_REQUEST.value());
            this.setMessage("VALIDATION_ERROR");

            this.fields = new ArrayList<>();
            for (FieldError error : ex.getBindingResult().getFieldErrors()) {

                addField(error.getField(), error.getDefaultMessage(), error.getObjectName());
            }
        }

        private void addField(String field, String message, String objectName) {
            FieldMessage fieldMessage = new FieldMessage(field, message, objectName);
            this.fields.add(fieldMessage);
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class FieldMessage {
        private String field;
        private String message;
        private String objectName;
    }

}
