package com.example.userservice.exception;

public class UserNotFoundException extends AbstractException{
    public UserNotFoundException(String msg,String techInfo){
        super(msg,techInfo);
    }
}
