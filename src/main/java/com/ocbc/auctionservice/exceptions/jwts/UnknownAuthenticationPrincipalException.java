package com.ocbc.auctionservice.exceptions.jwts;

public class UnknownAuthenticationPrincipalException extends JwtAuthenticationException {
    public UnknownAuthenticationPrincipalException(String message){ super(message); }
}
