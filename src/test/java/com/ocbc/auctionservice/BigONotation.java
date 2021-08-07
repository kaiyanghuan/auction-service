package com.ocbc.auctionservice;

import com.ocbc.auctionservice.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BigONotation {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();


    }

    private static void constantMethod(List<User> users){
        int x = 10;
        for (int i=0; i<10; i++){
            x += 1;
        }
    }

    private static void linearMethod(List<User> users){
        int x = 10;
        users.stream().forEach(user -> {
            user.getId();
            x += 1;
        });
    }

    private static void somethingMethod(List<User> users){
        users.stream().map(user -> {
            String name = user.getName();
            if (user.getId() > 5){
                name.equals("Kaiyang");
                //then do something with name
            }
        }).map(user -> {
            String name = user.getName();
            if (user.getId() < 1) {
                name.equals("Kaiyang");
                //then do something
            }
        });

        users.stream().filter(user -> {
            return user.getId() > 35 && user.getId() < 1;
        }).map(user -> {
            return user.getName();
        })
    }

    private static void linearMethod2(List<User> users){
        int x = 10;

        users.stream().forEach(user -> {
            user.getId();
        });
        // + plus

        users.stream().forEach(user -> {
            user.getId();
        });
    }

    private static void quadraticMethod(List<User> users){
        users.stream().forEach(user1 -> {
            users.stream().forEach(user2 -> {
                user1.equals(user2);
            });
            // * multiply
        });
    }

    private static void logMethod(List<User> users){
        int index = users.size()-1;
        while(index > 0){
            users.get(index);
            index /= 2;
        }
    }


    private static void logQuadMethod(List<User> users){
        users.stream().forEach(user -> {
            int index = users.size()-1;
            while(index > 0){
                users.get(index);
                index /= 2;
            }
            // 10 * log(10)
            // n * log(n)
            // * multiply
        });
    }

    private static void someOtherMethod(Optional<User> optionalUser){
        User user = optionalUser.get();
        User newUser = User.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .id(user.getId())
                .build();
    }
}
