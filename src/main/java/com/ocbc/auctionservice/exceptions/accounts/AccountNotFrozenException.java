package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountNotFrozenException extends BusinessException {
    public AccountNotFrozenException(String errorMessage){
        super(errorMessage);
    }
}