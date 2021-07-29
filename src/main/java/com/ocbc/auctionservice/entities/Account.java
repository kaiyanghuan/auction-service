package com.ocbc.auctionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_number")
    private String accountNumber;

    /*
    // Account Types are: ['C', 'D']
     */
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "last_active_date")
    private Date lastActiveDate;

    @Column(name = "account_start_date")
    private Date accountStartDate;

    @Column(name = "freeze")
    private boolean isFreeze;

    @Column(name = "user_id")
    private boolean userId;

    public enum AccountType {C, D}
}
