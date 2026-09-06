package com.laudado.talentforgeaibackend.exception;


public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(String message){
        super(message);
    }
}
