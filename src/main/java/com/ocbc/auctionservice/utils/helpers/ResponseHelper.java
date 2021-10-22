package com.ocbc.auctionservice.utils.helpers;

import com.ocbc.auctionservice.controllers.responses.AccountResponse;
import com.ocbc.auctionservice.entities.Account;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResponseHelper {

    public static class AccountResponseHelper {
        private Account account;

        public AccountResponseHelper(Account account) {
            this.account = account;
        }

        public AccountResponse toAccountResponse() {
            return AccountResponse.builder()
                    .id(UUID.fromString(account.getId()))
                    .accountNumber(account.getAccountNumber())
                    .accountType(account.getAccountType())
                    .status(account.getStatus())
                    .value(account.getValue().doubleValue())
                    .currency(account.getCurrency())
                    .user(account.getUser())
                    .build();
        }

    }

    public static AccountResponseHelper from(Account account) {
        return new AccountResponseHelper(account);
    }
}
