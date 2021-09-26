package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountFrozenException extends BusinessException {
    public AccountFrozenException(String errorMessage){
        super(errorMessage);
    }
}