package com.subhashCart.exceptions;

public class CustomerException extends RuntimeException{

    public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
    }
}
