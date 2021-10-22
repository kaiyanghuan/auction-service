package com.ocbc.auctionservice.exceptions.users;

import com.ocbc.auctionservice.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
