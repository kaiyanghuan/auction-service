package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountCannotBeClosedException extends BusinessException {
    public AccountCannotBeClosedException(String errorMessage){
        super(errorMessage);
    }
}