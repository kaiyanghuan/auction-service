package com.ocbc.auctionservice.repositories;

import com.ocbc.auctionservice.controllers.requests.AccountQueryRequest;
import com.ocbc.auctionservice.entities.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountRepositoryCustom {
    List<Account> findAllAccountsByQuery(AccountQueryRequest accountQueryRequest);
    Page<Account> findAllPageAccountsByQuery(AccountQueryRequest accountQueryRequest);
}
