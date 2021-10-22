package com.ocbc.auctionservice.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
