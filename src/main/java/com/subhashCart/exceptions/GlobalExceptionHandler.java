package com.subhashCart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest wr){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), ce.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> customerNotFoundExceptionHandler(CustomerNotFoundException cnfe, WebRequest wr){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), cnfe.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorDetails> sellerExceptionHandler(SellerException slre, WebRequest wr){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), slre.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
