//package com.ocbc.auctionservice.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import java.util.UUID;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @Column(name = "id")
//    private String id = UUID.randomUUID().toString();
//
//    @Column(name = "product_id")
//    private String productId;
//
//    @Column(name = "tenant_id")
//    private String tenantId;
//
//    @Column(name = "account_id")
//    private String accountId;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id", insertable = false, updatable = false)
//    private Account follower;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", insertable = false, updatable = false)
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "tenant_id", insertable = false, updatable = false)
//    private Tenant tenant;
//
//    @Column(name = "comment", columnDefinition = "MEDIUMTEXT")
//    private String comments;
//
//    @Column(name = "rating")
//    @Min(0)
//    @Max(5)
//    // Rating should be 0 stars to 5 stars
//    private Integer rating;
//}
