package com.streamparty.backend.service;

import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void createNewUser(User newUser){
        userRepository.save(newUser);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public void addFriend(String username1, String username2){
        User user1 = userRepository.findByUsername(username1);
        User user2 = userRepository.findByUsername(username2);

        user1.addFriend(username2);
        user2.addFriend(username1);

        userRepository.save(user1);
        userRepository.save(user2);
    }
    
}
