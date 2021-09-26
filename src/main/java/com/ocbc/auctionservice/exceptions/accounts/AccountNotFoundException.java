package com.ocbc.auctionservice.exceptions.accounts;

import com.ocbc.auctionservice.exceptions.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
