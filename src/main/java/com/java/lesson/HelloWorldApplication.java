package com.java.lesson;

import com.ocbc.auctionservice.entities.Account;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class HelloWorldApplication {

    public static void main(String[] args) {
//        System.out.println("Hello world");
//
//        String string = "Hello World";
////
////        final int x = 0;
////        final int y = 0;
//
//        String number = "123";
//        Integer.parseInt(number);
//
//        System.out.println(number instanceof String);
//
//        List<String> list = new ArrayList<>();
//        String text = "default";
//        if(!list.isEmpty()){
//            text = list.get(0);
//        }
//
//        for(int i=0; i<10; i+=2){
//
//        }
//        int day = 2;
//        switch(day){
//            case 1:
//                System.out.println("Monday");
//                break
//            case 2:
//        }
//        Map.of("key1","value1","key2","value2");
//
//        list.contains(text);
//
//        System.out.println(String.format("this can place variable %s", string));

        referenceIssues();
    }

//    public void getMoneyFromAccount(Account account){
//    }
//    public void getMoneyFromAccount(){
//    }

    public static void referenceIssues(){
        int x = 0;
        int y = 1;

        System.out.println(x); // 0
        System.out.println(y); // 1
        System.out.println();

        x = y; // x now becomes 1
        y += 1; //y now becomes 2
        System.out.println(x); // 1
        System.out.println(y); // 2
        System.out.println();

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        xList.add(1); // [1]
        yList.add(2); // [2]

        System.out.println(xList.size()); // [1] -> 1
        System.out.println(xList.get(0)); // 1
        System.out.println(yList.size()); // [2] -> 1
        System.out.println(yList.get(0)); // 2
        System.out.println();

        xList = yList; // [2]
        yList.add(3); // [2,3], xList also becomes [2,3]

        System.out.println(xList.size()); // [2, 3]
        System.out.println(xList.get(0)); // 2
        System.out.println(xList.get(1)); // 3
        System.out.println(yList.size()); // [2,3] -> 2
        System.out.println(yList.get(0)); // 2
        System.out.println(yList.get(1)); // 3

        Account accountA = Account.builder()
                .accountNumber("123412")
                .value(BigDecimal.TEN)
                .build();
        Account accountB = Account.builder()
                .accountNumber("5151897")
                .value(BigDecimal.ZERO)
                .build();

        accountA = accountB;
        accountB = updateAccount(accountB);

        List<Account> accounts = List.of(accountA, accountB);
        List<BigDecimal> allValues = new ArrayList<>();
        for (Account account : accounts){
            allValues.add(account.getValue());
        }

//        allValues = []
//        for(account in accounts):
//            allValues.append(account.value)

        accounts.forEach(account -> allValues.add(account.getValue()));
        List<String> allCurrencies = accounts.stream().map(Account::getCurrency).collect(Collectors.toList());
        List<String> allCurrenciesFiltered = accounts.stream()
                .filter(account -> account.getValue().compareTo(BigDecimal.TEN) == 1)
                .map(Account::getCurrency)
                .collect(Collectors.toList());
        List<Account> allAccountsFiltered = accounts.stream()
                .filter(account -> account.getValue().compareTo(BigDecimal.TEN) == 1)
                .collect(Collectors.toList());
        BigDecimal totalSum = accounts.stream()
                .map(Account::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(accountA.getValue()); //

    }

    public static Account updateAccount(Account other){
        other.setValue(BigDecimal.valueOf(1000L));
        return other;
    }

//    {"key": "value", "key2":"value2"}
}


//for (i in range(10)):

//for (i in range(0,10,2)):
//def (a, b):