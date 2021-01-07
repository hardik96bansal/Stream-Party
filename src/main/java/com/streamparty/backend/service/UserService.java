package com.streamparty.backend.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.streamparty.backend.dto.RoomDto;
import com.streamparty.backend.dto.UserDto;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getUserDtoByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return new UserDto(user);
    }

    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }

    public List<RoomDto> getUserRooms(String username){
        User user = userRepository.findByUsername(username);
        if(user == null || user.getRooms()==null) return null;
        return user.getRooms().stream().map(room -> new RoomDto(room)).collect(Collectors.toList());
    }

    public void createNewUser(User newUser){
        userRepository.save(newUser);
    }

    public void updateUser(User updatedUser){
        User user = userRepository.findByUsername(updatedUser.getUsername());
        if(user!=null){
            user.setActive(true);
            user.setPassword(updatedUser.getPassword());
            user.setImage(updatedUser.getImage());
            userRepository.save(user);
        }
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }



}
