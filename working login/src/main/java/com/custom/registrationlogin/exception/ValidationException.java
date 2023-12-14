package com.custom.registrationlogin.exception;

public class ValidationException extends RuntimeException
{
    public ValidationException(String message){
        super(message);
    }
}
