package com.ocbc.auctionservice.services;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.exceptions.users.UserAlreadyExistException;
import com.ocbc.auctionservice.exceptions.users.UserNotFoundException;
import com.ocbc.auctionservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        log.info("Retrieve all users from database");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User's id %s does not exist", id)));
    }

    @Transactional(readOnly = true)
    public User getUser(String name) {
        return userRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException(String.format("User's name %s does not exist", name)));
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(String.format("Username %s does not exist", username)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //Just a sample
//    public Account doSomethingWithAccount(Account account){
//        // This one refer to jira ticket 5506 https://jira.com/ticket/5506
//        if (account.someSortOfAccountCondition()){
//            //TODO: Setting this like this for now, going to change when we get more updates from BA
//            //TODO: Please remove this part of the code when BA comes back something
//        }
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUser(User user, int id) {
        User existingUser = getUser(id);
        existingUser.setAge(user.getAge());
        existingUser.setAddress(user.getAddress());
        existingUser.setRoles(user.getRoles());
        existingUser.setPermissions(user.getPermissions());
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        User existingUser = getUser(id);
        userRepository.delete(existingUser);
    }
}
