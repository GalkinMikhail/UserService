package com.example.userservice.exception;

public class BadCredentialsException extends AbstractException{

    public BadCredentialsException(String msg,String techInfo){
        super(msg,techInfo);
    }
}
