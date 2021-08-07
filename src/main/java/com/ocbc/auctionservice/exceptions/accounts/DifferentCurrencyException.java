package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class DifferentCurrencyException extends BusinessException {
    public DifferentCurrencyException(String errorMessage){
        super(errorMessage);
    }
}