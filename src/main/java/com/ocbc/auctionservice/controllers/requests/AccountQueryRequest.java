package com.ocbc.auctionservice.controllers.requests;

import com.ocbc.auctionservice.entities.Account;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountQueryRequest {
    private Account.AccountType accountType;
    private Account.AccountStatus accountStatus;
    private BigDecimal startValue;
    private BigDecimal endValue;
    private String currency;
    private Integer userId;
    private AppPageRequest pageRequest;
}
