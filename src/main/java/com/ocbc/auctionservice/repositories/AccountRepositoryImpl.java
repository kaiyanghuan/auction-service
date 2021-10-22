package com.ocbc.auctionservice.repositories;

import com.ocbc.auctionservice.controllers.requests.AccountQueryRequest;
import com.ocbc.auctionservice.controllers.requests.AppPageRequest;
import com.ocbc.auctionservice.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Account> findAllAccountsByQuery(AccountQueryRequest accountQueryRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

        Root<Account> accountRoot = criteriaQuery.from(Account.class);
        List<Predicate> predicates = new ArrayList<>();

        if (accountQueryRequest.getAccountType() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("accountType"), accountQueryRequest.getAccountType()));
        }

        if (accountQueryRequest.getAccountStatus() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("status"), accountQueryRequest.getAccountStatus()));
        }

        if (accountQueryRequest.getStartValue() != null && accountQueryRequest.getEndValue() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(accountRoot.get("value"), accountQueryRequest.getStartValue()));
            predicates.add(criteriaBuilder.lessThan(accountRoot.get("value"), accountQueryRequest.getEndValue()));
        }

        if (accountQueryRequest.getCurrency() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("currency"), accountQueryRequest.getCurrency()));
        }

        if (accountQueryRequest.getUserId() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("userId"), accountQueryRequest.getUserId()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery)
                .setMaxResults(accountQueryRequest.getPageRequest().getSize())
                .setFirstResult(accountQueryRequest.getPageRequest().getPage())
                .getResultList();
    }


    @Override
    public Page<Account> findAllPageAccountsByQuery(AccountQueryRequest accountQueryRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

        Root<Account> accountRoot = criteriaQuery.from(Account.class);
        List<Predicate> predicates = new ArrayList<>();

        if (accountQueryRequest.getAccountType() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("accountType"), accountQueryRequest.getAccountType()));
        }

        if (accountQueryRequest.getAccountStatus() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("status"), accountQueryRequest.getAccountStatus()));
        }

        if (accountQueryRequest.getStartValue() != null && accountQueryRequest.getEndValue() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(accountRoot.get("value"), accountQueryRequest.getStartValue()));
            predicates.add(criteriaBuilder.lessThan(accountRoot.get("value"), accountQueryRequest.getEndValue()));
        }

        if (accountQueryRequest.getCurrency() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("currency"), accountQueryRequest.getCurrency()));
        }

        if (accountQueryRequest.getUserId() != null) {
            predicates.add(criteriaBuilder.equal(accountRoot.get("userId"), accountQueryRequest.getUserId()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        AppPageRequest pageRequest = accountQueryRequest.getPageRequest();
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), pageRequest.getDirection(), pageRequest.getField());
        Query query = entityManager.createQuery(criteriaQuery);
        return new PageImpl<>(query.getResultList(), pageable, pageRequest.getTotal());
    }
}
