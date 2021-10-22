package com.ocbc.auctionservice.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreditRequest {
    private UUID accountId;
    private String currency;
    private BigDecimal value;
}
