package com.user.auth.exception;

public class CustomException extends Exception{
    private String message;

    public CustomException(String message){
        this.message=message;

    }

}
