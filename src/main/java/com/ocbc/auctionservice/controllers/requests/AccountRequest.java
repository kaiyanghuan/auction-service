package com.ocbc.auctionservice.controllers.requests;

import com.ocbc.auctionservice.entities.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {
    private String accountNumber;
    private Account.AccountType accountType;
    private String currency;
    private boolean userId;
}
