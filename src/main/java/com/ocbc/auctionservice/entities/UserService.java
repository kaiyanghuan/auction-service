package com.ocbc.auctionservice.entities;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users;

    public void initialiseUser(){
        users = new ArrayList<>();
        users.add(new User(1,"Alex","central", 19));
        users.add(new User(2,"Sally","bishan", 26));
        users.add(new User(3,"Tim","ang mo kio", 52));
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(int id){
        return users.stream().filter(user -> user.getId() == id)
                .findFirst().orElseGet(null);
    }

    public User createUser(User user){
        users.add(user);
        return user;
    }

}
