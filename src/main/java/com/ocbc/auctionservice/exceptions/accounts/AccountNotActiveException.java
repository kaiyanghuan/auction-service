package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountNotActiveException extends BusinessException {
    public AccountNotActiveException(String errorMessage){
        super(errorMessage);
    }
}