package com.ocbc.auctionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account extends Auditable {

    @Id
    @Column(name = "id", length = 100)
    private String id = UUID.randomUUID().toString();

    @Column(name = "account_number")
    private String accountNumber = UUID.randomUUID().toString();;

    // Account Types are: SAVINGS, CURRENTS, GLOBAL_SAVINGS_ACCOUNT
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    // Account Status are: PENDING, ACTIVE, FROZEN, CLOSED
    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.PENDING;

    @Column(name = "value")
    private BigDecimal value = BigDecimal.ZERO;

    @Column(name = "currency")
    private String currency;

    @Column(name = "last_active_date")
    private Date lastActiveDate = new Date();

    @Column(name = "account_start_date")
    private Date accountStartDate = new Date();

    @Column(name = "user_id")
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public enum AccountType { SAVINGS, CURRENT, GLOBAL_SAVINGS_ACCOUNT }

    public enum AccountStatus { PENDING, ACTIVE, FROZEN, CLOSED }

    public BigDecimal creditValue(BigDecimal value){
        return this.value.add(value);
    }

    public BigDecimal debitValue(BigDecimal value){
        return this.value.subtract(value);
    }

    public Boolean isNotFrozen(){
        return status != AccountStatus.FROZEN;
    }

    public Boolean activeAccount() {
        return status == AccountStatus.ACTIVE;
    }

    public Boolean isNotActive() {
        return !activeAccount();
    }

    public Boolean isNotActiveOrFrozen() {
        return isNotActive() && isNotFrozen();
    }
}
