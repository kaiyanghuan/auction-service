package com.ocbc.auctionservice.exceptions;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
