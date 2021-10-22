package com.ocbc.auctionservice.services;

import com.ocbc.auctionservice.controllers.requests.AccountQueryRequest;
import com.ocbc.auctionservice.controllers.requests.CreditRequest;
import com.ocbc.auctionservice.controllers.requests.PaymentRequest;
import com.ocbc.auctionservice.controllers.requests.TransferRequest;
import com.ocbc.auctionservice.controllers.responses.TransferResponse;
import com.ocbc.auctionservice.entities.Account;
import com.ocbc.auctionservice.exceptions.accounts.*;
import com.ocbc.auctionservice.repositories.AccountRepository;
import com.ocbc.auctionservice.utils.helpers.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.ocbc.auctionservice.repositories.AccountSpecifications.hasValueBetween;

@Service
public class AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ResponseHelper responseHelper;

    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public List<Account> getAccountsWithSpecificationAndPageable(Pageable pageable){
        Page<Account> accountPages = accountRepository.findAll((Specification) hasValueBetween(BigDecimal.valueOf(1000L), BigDecimal.valueOf(10000L)),pageable);
        return accountRepository.findAll((Specification) hasValueBetween(BigDecimal.valueOf(1000L), BigDecimal.valueOf(10000L)));
    }

    public List<Account> getAllAccounts(AccountQueryRequest accountQueryRequest) {
        return accountRepository.findAllAccountsByQuery(accountQueryRequest);
    }

    public Page<Account> getAllPageAccounts(AccountQueryRequest accountQueryRequest) {
        return accountRepository.findAllPageAccountsByQuery(accountQueryRequest);
    }

    public List<Account> getAccounts(Integer userId) {
        return accountRepository.findAllByUserIdAndStatusNot(userId, Account.AccountStatus.CLOSED);
    }

    public List<Account> getActiveAccounts(Integer userId) {
        return accountRepository.findAllByUserIdAndStatus(userId, Account.AccountStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public Account getAccount(String id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(String.format("Account id %s does not exist", id)));
    }

    @Transactional(readOnly = true)
    public Account getActiveAccount(String id) {
        Account account = getAccount(id);
        validateAccountActive(account);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccount(Account account) {
        userService.getUser(account.getUserId());
        validateAccountNumberAndCurrencyDoesNotExist(account.getAccountNumber(), account.getCurrency());
        if (accountRepository.findById(account.getId()).isPresent()) {
            throw new AccountAlreadyExistException(String.format("Account id %s already exist", account.getId()));
        }
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account approveAccount(String id) {
        Account account = getAccount(id);
        account.setLastActiveDate(new Date());
        account.setStatus(Account.AccountStatus.ACTIVE);
        return accountRepository.save(account);
    }

    @Transactional
    public Account unfreezeAccount(String id) {
        Account frozenAccount = getAccount(id);
        validateAccountUnfreezeCriteria(frozenAccount);
        frozenAccount.setStatus(Account.AccountStatus.ACTIVE);
        return accountRepository.save(frozenAccount);
    }

    @Transactional
    public Account freezeAccount(String id) {
        Account account = getAccount(id);
        validateAccountActive(account);
        account.setStatus(Account.AccountStatus.FROZEN);
        return accountRepository.save(account);
    }

    @Transactional
    public Account closeAccount(String id) {
        Account account = getAccount(id);
        validateAccountCloseCriteria(account);
        account.setStatus(Account.AccountStatus.CLOSED);
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TransferResponse transfers(TransferRequest transferRequest) {

        Account fromAccount = getAccount(transferRequest.getFromAccountId().toString());
        Account toAccount = getAccount(transferRequest.getToAccountId().toString());
        validateTransfers(fromAccount, toAccount, transferRequest.getCurrency());
        return TransferResponse.builder()
                .fromAccount(ResponseHelper.from(creditAccount(toAccount, transferRequest.getValue())).toAccountResponse())
                .toAccount(ResponseHelper.from(debitAccount(fromAccount, transferRequest.getValue())).toAccountResponse())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account credit(CreditRequest creditRequest) {
        Account account = getAccount(creditRequest.getAccountId().toString());
        validateAccountActive(account);
        validateCurrency(account.getCurrency(), creditRequest.getCurrency());
        return creditAccount(account, creditRequest.getValue());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account payment(PaymentRequest paymentRequest) {
        Account account = getAccount(paymentRequest.getAccountId().toString());
        validateAccountActive(account);
        validateCurrency(account.getCurrency(), paymentRequest.getCurrency());
        return debitAccount(account, paymentRequest.getValue());
    }

    private Account creditAccount(Account account, BigDecimal value) {
        account.creditValue(value);
        account.setLastActiveDate(new Date());
        return accountRepository.save(account);
    }

    private Account debitAccount(Account account, BigDecimal value) {
        account.debitValue(value);
        account.setLastActiveDate(new Date());
        return accountRepository.save(account);
    }

    private void validateTransfers(Account fromAccount, Account toAccount, String currency) {
        // Only active accounts can transfer out
        validateAccountActive(fromAccount);
        validateAccountOrFrozen(toAccount);
        validateCurrency(fromAccount.getCurrency(), currency);
        validateCurrency(toAccount.getCurrency(), currency);
    }

    private void validateAccountOrFrozen(Account account) {
        if (account.isNotActiveOrFrozen()) {
            throw new AccountIsNeitherActiveNorFrozenException(
                    String.format("Account %s is neither active nor frozen to retrieve transaction", account.getAccountNumber()));
        }
    }

    private void validateCurrency(String accountCurrency, String requestCurrency) {
        if (!accountCurrency.equals(requestCurrency)) {
            throw new DifferentCurrencyException(
                    String.format("Currency does not match, from account uses %s while the request currency is %s",
                            accountCurrency, requestCurrency)
            );
        }
    }

    private void validateAccountActive(Account account) {
        if (account.isNotActive()) {
            throw new AccountNotActiveException(String.format("Account %s is not active to conduct transaction", account.getAccountNumber()));
        }
    }

    private void validateAccountCloseCriteria(Account account) {
        validateAccountActive(account);
        if (!account.getValue().equals(BigDecimal.ZERO)) {
            throw new AccountCannotBeClosedException(
                    String.format("Account %s still have %s %s of value in it",
                            account.getAccountNumber(),
                            account.getValue(),
                            account.getCurrency())
            );
        }
    }

    private void validateAccountUnfreezeCriteria(Account frozenAccount) {
        if (frozenAccount.isNotFrozen()) {
            throw new AccountNotFrozenException(String.format("Account %s was not frozen", frozenAccount.getAccountNumber()));
        }
        if (frozenAccount.getLastActiveDate().before(minusDaysFromDate(30, new Date()))) {
            throw new AccountCannotBeUnfreezeException(String.format("Account %s cannot be unfreeze due to last active date is longer than 30 days", frozenAccount.getAccountNumber()));
        }
    }

    private void validateAccountNumberAndCurrencyDoesNotExist(String accountNumber, String currency) {
        if (accountRepository.findByAccountNumberAndCurrency(accountNumber, currency).isPresent()) {
            throw new AccountAlreadyExistException(String.format("Account number %s with currency %s already exist", accountNumber, currency));
        }
    }

    private Date minusDaysFromDate(Integer days, Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(localDateTime.minusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }
}
