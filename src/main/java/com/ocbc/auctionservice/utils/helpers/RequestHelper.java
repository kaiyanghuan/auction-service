package com.ocbc.auctionservice.utils.helpers;

import com.ocbc.auctionservice.controllers.requests.AccountRequest;
import com.ocbc.auctionservice.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class RequestHelper {

    public static class AccountRequestHelper {
        private AccountRequest accountRequest;

        public AccountRequestHelper(AccountRequest accountRequest) {
            this.accountRequest = accountRequest;
        }

        public Account toAccount() {
            Account account = new Account();
            account.setAccountType(accountRequest.getAccountType());
            account.setCurrency(accountRequest.getCurrency());
            account.setUserId(accountRequest.getUserId());
            return account;
        }
    }

    public static AccountRequestHelper from(AccountRequest accountRequest) {
        return new AccountRequestHelper(accountRequest);
    }
}
