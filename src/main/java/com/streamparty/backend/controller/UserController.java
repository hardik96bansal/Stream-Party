package com.streamparty.backend.controller;

import com.streamparty.backend.model.User;
import com.streamparty.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/user/{username}")
    public User getUserbyUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    public void newUser(@RequestBody User newUser){
        userService.createNewUser(newUser);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @GetMapping("/friend/{user1}/{user2}")
    public void createFriends(@PathVariable String user1, @PathVariable String user2){
        userService.addFriend(user1, user2);
    }

}
