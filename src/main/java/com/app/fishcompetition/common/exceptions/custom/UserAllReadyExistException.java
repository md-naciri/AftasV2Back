package com.app.fishcompetition.common.exceptions.custom;

public class UserAllReadyExistException  extends RuntimeException{
    public  UserAllReadyExistException(String message){
        super(message);
    }
}
