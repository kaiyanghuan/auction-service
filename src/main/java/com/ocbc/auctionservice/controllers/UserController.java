package com.ocbc.auctionservice.controllers;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/initialise")
    public String initialise(){
        userService.initialiseUser();
        return "ok";
    }


    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

//    @PutMapping
//    public User updateUser(){
//
//    }
//
//    @DeleteMapping User deleteUser(){
//
//    }
}
