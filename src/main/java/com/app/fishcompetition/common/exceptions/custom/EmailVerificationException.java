package com.app.fishcompetition.common.exceptions.custom;

public class EmailVerificationException  extends  RuntimeException{
    public EmailVerificationException(String message){
        super(message);
    }
}
