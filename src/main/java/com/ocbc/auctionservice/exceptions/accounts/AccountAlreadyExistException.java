package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountAlreadyExistException extends BusinessException {
    public AccountAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}