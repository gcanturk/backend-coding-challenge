package com.journi.challenge.controllers;

import com.journi.challenge.exceptions.PurchaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PurchaseExceptionHandler {
    @ExceptionHandler(value = PurchaseNotFoundException.class)
    public ResponseEntity<Object> exception(PurchaseNotFoundException exception) {
        return new ResponseEntity<>("No purchases not found.", HttpStatus.NOT_FOUND);
    }
}