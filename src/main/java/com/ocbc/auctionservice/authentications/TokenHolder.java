package com.ocbc.auctionservice.authentications;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenHolder {

    private TokenType tokenType;
    private String token;

    enum TokenType{
        USER, API_CLIENT
    }
}
