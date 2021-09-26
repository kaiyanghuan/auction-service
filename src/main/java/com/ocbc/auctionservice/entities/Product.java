//package com.ocbc.auctionservice.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.UUID;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "products")
//public class Product extends Auditable {
//
//    @Id
//    @Column(name = "id")
//    private String id = UUID.randomUUID().toString();
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "price")
//    private Double price;
//
//    @Column(name = "details")
//    private String details;
//
//    @Column(name = "currency")
//    private String currency;
//
//    @Column(name = "ratings")
//    private BigDecimal ratings;
//
//    @Column(name = "ratings")
//    private Tenant tenant;
//
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
//    private List<Review> reviews;
//}
