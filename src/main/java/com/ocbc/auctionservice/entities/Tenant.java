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
//@Entity
//@Builder
//@Data
//@Table(name = "tenants")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Tenant extends Auditable {
//
//    @Id
//    @Column(name = "id")
//    private String id = UUID.randomUUID().toString();
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "rating")
//    private BigDecimal rating;
//
//    @Column(name = "ratings")
//    private BigDecimal ratings;
//
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Follow> followers;
//
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
//    private List<Product> products;
//
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
//    private List<Review> reviews;
//}
