package com.ocbc.auctionservice.controllers;

import com.ocbc.auctionservice.authentications.UserContext;
import com.ocbc.auctionservice.configurations.RequestSynchronizationManager;
import com.ocbc.auctionservice.controllers.requests.UserRequest;
import com.ocbc.auctionservice.controllers.responses.UserResponse;
import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestSynchronizationManager requestSynchronizationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        log.info("Request for all users for " + requestSynchronizationManager.loggedInUsername());
        return ResponseEntity.ok(userService.getUsers().stream().map(
                this::userToUserResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userToUserResponse(
                userService.getUser(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userToUserResponse(
                userService.createUser(userRequestToUser(userRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable int id) {
        return ResponseEntity.ok(userToUserResponse(
                userService.updateUser(userRequestToUser(userRequest), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("success");
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

    private UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .address(user.getAddress())
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
}
