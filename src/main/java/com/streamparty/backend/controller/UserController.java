package com.streamparty.backend.controller;

import java.util.List;

import com.streamparty.backend.dto.RoomDto;
import com.streamparty.backend.dto.UserDto;
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
    public UserDto getUserbyUsername(@PathVariable("username") String username){
        return userService.getUserDtoByUsername(username);
    }

    @GetMapping("user/{username}/room")
    public List<RoomDto> getUserRooms(@PathVariable("username") String username){
        return userService.getUserRooms(username);
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

}
