package com.ocbc.auctionservice.exceptions;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
