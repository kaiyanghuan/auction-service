package com.ocbc.auctionservice.controllers.responses;

import lombok.Builder;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String tokenType = "Bearer";
    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
