package com.subhashCart.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginException extends RuntimeException{


    public LoginException(String message) {
        super(message);
    }

}
