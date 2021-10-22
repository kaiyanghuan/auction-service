package com.ocbc.auctionservice.repositories;

import com.ocbc.auctionservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ocbc.auctionservice.entities.Account.AccountStatus;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, String>, AccountRepositoryCustom , JpaSpecificationExecutor  {

    Optional<Account> findByAccountNumberAndCurrency(String accountNumber, String currency);

    List<Account> findAllByUserIdAndStatusNot(Integer userId, AccountStatus accountStatus);

    List<Account> findAllByUserIdAndStatus(Integer userId, AccountStatus accountStatus);
}
