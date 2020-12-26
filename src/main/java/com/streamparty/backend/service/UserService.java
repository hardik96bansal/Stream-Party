package com.streamparty.backend.service;

import com.streamparty.backend.model.Room;
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

    public void addUserToRoom(String username, Room room){
        User currUser = userRepository.findByUsername(username);
        if(currUser != null) currUser.addRoom(room);
    }

    public void removeUserFromRoom(String username, Room room){
        User currUser = userRepository.findByUsername(username);
        if(currUser!=null) currUser.removeRoom(room);
    }

}
