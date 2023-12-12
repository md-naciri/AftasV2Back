package com.app.fishcompetition.common.exceptions.custom;

import org.springframework.stereotype.Component;

public class MemberCompetitionAlreadyExistException extends  RuntimeException{
    public  MemberCompetitionAlreadyExistException(String message){
        super(message);
    }
}
