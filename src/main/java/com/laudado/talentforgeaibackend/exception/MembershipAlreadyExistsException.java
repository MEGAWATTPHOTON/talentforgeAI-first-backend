package com.laudado.talentforgeaibackend.exception;

public class MembershipAlreadyExistsException extends RuntimeException{
    public MembershipAlreadyExistsException(String message){
        super(message);
    }
}
