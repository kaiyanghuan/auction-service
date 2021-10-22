package com.ocbc.auctionservice.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiReponse {
    private boolean success;
    private String message;
}
