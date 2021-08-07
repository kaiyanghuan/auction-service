package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.BusinessException;

public class AccountCannotBeUnfreezeException extends BusinessException {
    public AccountCannotBeUnfreezeException(String errorMessage){
        super(errorMessage);
    }
}