package com.streamparty.backend.service;

import com.streamparty.backend.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserByUsername(String username){
        return new User("Hardik", "pass", true, "img1 ");
    }
    
}
