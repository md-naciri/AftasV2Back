package com.app.fishcompetition.common.exceptions.custom;

public class InvalidOauth2Exception extends  RuntimeException{

    public InvalidOauth2Exception(String message ){
        super(message);
    }
}
