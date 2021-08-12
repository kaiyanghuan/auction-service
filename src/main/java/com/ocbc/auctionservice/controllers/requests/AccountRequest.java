package com.ocbc.auctionservice.controllers.requests;

import com.ocbc.auctionservice.entities.Account;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AccountRequest {

    @NotNull(message = "{AccountRequest.accountType.notNul}")
    private Account.AccountType accountType;

    @NotEmpty(message = "{AccountRequest.currency.NotEmpty}")
    private String currency;

    @NotNull(message = "{AccountRequest.userId.notNul}")
    private Integer userId;
}
