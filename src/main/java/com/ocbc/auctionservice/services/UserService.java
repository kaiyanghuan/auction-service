package com.ocbc.auctionservice.services;

import com.ocbc.auctionservice.entities.User;
import com.ocbc.auctionservice.exceptions.UserAlreadyExistException;
import com.ocbc.auctionservice.exceptions.UserNotFoundException;
import com.ocbc.auctionservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistException(String.format("User id %s already exist", user.getId()));
        }
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
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        User existingUser = getUser(id);
        userRepository.delete(existingUser);
    }
}
