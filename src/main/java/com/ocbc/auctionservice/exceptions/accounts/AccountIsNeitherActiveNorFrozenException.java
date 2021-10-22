package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountIsNeitherActiveNorFrozenException extends BusinessException {
    public AccountIsNeitherActiveNorFrozenException(String errorMessage){
        super(errorMessage);
    }
}