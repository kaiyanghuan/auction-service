package com.ocbc.auctionservice.resolvers;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver {

    @Autowired
    private UserService userService;

    public User user(Integer id){
       return userService.getUser(id);
    }
    public List<User> users(Integer count){
        return userService.getUsers();
    }
}
