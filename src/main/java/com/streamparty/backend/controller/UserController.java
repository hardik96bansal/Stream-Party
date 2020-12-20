package com.streamparty.backend.controller;

import com.streamparty.backend.model.User;
import com.streamparty.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/user/{username}")
    public User temp(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
}
