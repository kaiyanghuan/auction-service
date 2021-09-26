package com.ocbc.auctionservice.exceptions.jwts;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String errorMessage) {
        super(errorMessage);
    }
}
