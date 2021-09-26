package com.ocbc.auctionservice.exceptions.jwts;

public class JwtExpiredException extends JwtAuthenticationException {
    public JwtExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
