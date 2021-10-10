package com.ocbc.auctionservice.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ocbc.auctionservice.controllers.requests.UserRequest;
import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRequest input){
        return userService.createUser(userRequestToUser(input));
    }

    private User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(userRequest.getRoles())
                .permissions(userRequest.getPermissions())
                .username(userRequest.getName().toLowerCase())
                .build();
    }
}
