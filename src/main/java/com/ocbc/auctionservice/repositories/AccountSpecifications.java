package com.ocbc.auctionservice.repositories;

import com.ocbc.auctionservice.entities.Account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;


public class AccountSpecifications {

    public static Specification<Account> hasValueBetween(BigDecimal startValue, BigDecimal endValue) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("value"), startValue),
                criteriaBuilder.greaterThanOrEqualTo(root.get("value"), endValue));
    }

    public static Specification<Account> isUser(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification<Account> hasCurrency(String currency) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("currency"), currency);
    }

    public static Specification<Account> withStatus(Account.AccountStatus status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Account> withType(Account.AccountType type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }
}