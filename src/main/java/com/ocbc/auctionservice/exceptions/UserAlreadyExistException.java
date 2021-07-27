package com.ocbc.auctionservice.exceptions;

public class UserAlreadyExistException extends BusinessException{
    public UserAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}