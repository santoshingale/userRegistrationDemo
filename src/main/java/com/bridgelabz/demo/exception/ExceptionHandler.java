package com.bridgelabz.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    private ResponseEntity userExceptionHandler(UserException userException) {
        return new ResponseEntity(userException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
