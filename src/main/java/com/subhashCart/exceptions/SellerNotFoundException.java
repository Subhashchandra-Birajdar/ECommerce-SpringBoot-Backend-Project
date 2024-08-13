package com.subhashCart.exceptions;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException() {
        super();
    }


    public SellerNotFoundException(String message) {
        super(message);
    }
}
