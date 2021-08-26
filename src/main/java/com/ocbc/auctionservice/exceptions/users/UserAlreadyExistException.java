package com.ocbc.auctionservice.exceptions.users;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}