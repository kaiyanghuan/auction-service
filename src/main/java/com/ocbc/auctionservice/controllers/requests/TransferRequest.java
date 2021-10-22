package com.ocbc.auctionservice.controllers.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {
    private UUID fromAccountId;
    private UUID toAccountId;
    private String currency;
    private BigDecimal value;
}
