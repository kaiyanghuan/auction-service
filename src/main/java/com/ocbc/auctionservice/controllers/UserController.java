package com.ocbc.auctionservice.controllers;

import com.ocbc.auctionservice.controllers.requests.UserRequest;
import com.ocbc.auctionservice.controllers.responses.UserResponse;
import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers().stream().map(
                user -> userToUserResponse(user)).collect(Collectors.toList()));
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
