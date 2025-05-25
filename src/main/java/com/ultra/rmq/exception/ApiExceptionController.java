package com.ultra.rmq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        ApiException apiException = new ApiException();
        apiException.setStatus(HttpStatus.BAD_REQUEST.value());
        apiException.setMessage(ex.getMessage());
        apiException.setTimestamp(LocalDateTime.now().toString());
        
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        ApiException apiException = new ApiException();
        apiException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiException.setMessage("An error occurred: " + ex.getMessage());
        apiException.setTimestamp(LocalDateTime.now().toString());
        
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
