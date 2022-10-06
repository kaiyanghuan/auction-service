package com.ocbc.auctionservice.controllers;

import com.ocbc.auctionservice.controllers.requests.*;
import com.ocbc.auctionservice.controllers.responses.AccountResponse;
import com.ocbc.auctionservice.controllers.responses.TransferResponse;
import com.ocbc.auctionservice.entities.Account;
import com.ocbc.auctionservice.services.AccountService;
import com.ocbc.auctionservice.utils.helpers.OnCreate;
import com.ocbc.auctionservice.utils.helpers.RequestHelper;
import com.ocbc.auctionservice.utils.helpers.ResponseHelper;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResponseHelper responseHelper;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/page")
    public ResponseEntity<Page<Account>> getAllAccounts(@RequestBody AppPageRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), pageRequest.getDirection(), pageRequest.getField());
        return ResponseEntity.ok(accountService.getAllAccounts(pageable));
    }

    @PostMapping("/query")
    public ResponseEntity<List<AccountResponse>> getAllAccountsByQuery(@RequestBody AccountQueryRequest accountQueryRequests) {
        return ResponseEntity.ok(accountService.getAllAccounts(accountQueryRequests).stream().map(account ->
                ResponseHelper.from(account).toAccountResponse()).collect(Collectors.toList()));
    }

    @PostMapping("/query/page")
    public ResponseEntity<Page<Account>> getAllAccountsByQueryPage(@RequestBody AccountQueryRequest accountQueryRequests) {
        return ResponseEntity.ok(accountService.getAllPageAccounts(accountQueryRequests));
    }


    @GetMapping("/{userId}/user")
    public ResponseEntity<List<AccountResponse>> getAccounts(@PathVariable Integer userId) {
        return ResponseEntity.ok(accountService.getAccounts(userId).stream().map(account ->
                ResponseHelper.from(account).toAccountResponse()).collect(Collectors.toList()));
    }

    @GetMapping("/{userId}/user/active")
    public ResponseEntity<List<AccountResponse>> getActiveAccounts(@PathVariable Integer userId) {
        return ResponseEntity.ok(accountService.getActiveAccounts(userId).stream().map(account ->
                ResponseHelper.from(account).toAccountResponse()).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String id) {
        String jwtToken = (String) this.request.getAttribute("x-jwt-token");
        return ResponseEntity.ok(ResponseHelper.from(accountService.getAccount(id))
                .toAccountResponse());
    }

    @GetMapping("/{id}/active")
    public ResponseEntity<AccountResponse> getActiveAccount(@PathVariable String id) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.getActiveAccount(id))
                .toAccountResponse());
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(ResponseHelper.from(
                accountService.createAccount(RequestHelper.from(accountRequest).toAccount()))
                .toAccountResponse());
    }

    @PreAuthorize("@AccountApprovalService.validateMakerChecker(id)")
    @PutMapping("/approval/{id}/approve")
    public ResponseEntity<AccountResponse> approveAccount(@PathVariable String id) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.approveAccount(id))
                .toAccountResponse());
    }

    @PutMapping("/approval/{id}/unfreeze")
    public ResponseEntity<AccountResponse> unfreezeAccount(@PathVariable String id) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.unfreezeAccount(id))
                .toAccountResponse());
    }

    @PutMapping("/approval/{id}/freeze")
    public ResponseEntity<AccountResponse> freezeAccount(@PathVariable String id) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.freezeAccount(id))
                .toAccountResponse());
    }

    @PutMapping("/approval/{id}/close")
    public ResponseEntity<AccountResponse> closeAccount(@PathVariable String id) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.closeAccount(id))
                .toAccountResponse());
    }

    @PostMapping("/transfers")
    public ResponseEntity<TransferResponse> transfers(@Valid TransferRequest transferRequest) {
        return ResponseEntity.ok(accountService.transfers(transferRequest));
    }

    @PostMapping("/credit")
    public ResponseEntity<AccountResponse> credit(@Valid CreditRequest creditRequest) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.credit(creditRequest)).toAccountResponse());
    }

    @PostMapping("/payment")
    public ResponseEntity<AccountResponse> payment(@Valid PaymentRequest paymentRequest) {
        return ResponseEntity.ok(ResponseHelper.from(accountService.payment(paymentRequest)).toAccountResponse());
    }
}
