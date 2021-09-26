package com.ocbc.auctionservice.controllers.responses;

import com.ocbc.auctionservice.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.ocbc.auctionservice.entities.Account.AccountStatus;
import static com.ocbc.auctionservice.entities.Account.AccountType;

@Data
@Builder
public class AccountResponse {

    private UUID id;
    private String accountNumber;
    private AccountType accountType;
    private AccountStatus status;
    private Double value;
    private String currency;
    private User user;
}
