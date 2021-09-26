//package com.ocbc.auctionservice.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.UUID;
//
//@Entity
//@Builder
//@Data
//@Table(name = "follows")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Follow {
//
//    @Id
//    @Column(name = "id")
//    private String id = UUID.randomUUID().toString();
//
//    @Column(name = "account_id")
//    private String accountId;
//
//    @Column(name = "tenant_id")
//    private String tenantId;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id", insertable = false, updatable = false)
//    private Account follower;
//
//    @ManyToOne
//    @JoinColumn(name = "tenant_id", insertable = false, updatable = false)
//    private Tenant tenant;
//}
