package com.streamparty.backend.controller;

import java.util.List;

import com.streamparty.backend.constants.RoomConstants;
import com.streamparty.backend.dto.RoomDto;
import com.streamparty.backend.dto.UserDto;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.service.RoomService;
import com.streamparty.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @GetMapping("/room/{roomId}")
    public Room getRoomById(@PathVariable("roomId") String roomId){
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/room/{username}")
    public ResponseEntity<String> createRoom(@RequestBody Room room, @PathVariable String username){

        if(room.getRoomName()==null || room.getPrivacy()==null){
            return new ResponseEntity<String>("Invalid data",HttpStatus.BAD_REQUEST);
        }

        Room createdRoom = roomService.createRoom(room.getRoomName(), username, room.getPrivacy());

        return new ResponseEntity<>("Room Created "+createdRoom.getRoomId(),HttpStatus.OK);
    }
    
    @GetMapping("/room/{roomId}/member")
    public List<UserDto> getRoomMembers(@PathVariable("roomId") String roomId){
        return roomService.getRoomMembers(roomId);
    }

    @GetMapping("/room/{roomId}/admin")
    public UserDto getRoomAdmin(@PathVariable("roomId") String roomId){
        return roomService.getRoomAdmin(roomId);
    }

    @PostMapping("room/{roomId}/join/{username}")
    public ResponseEntity<String> joinRoom(@PathVariable("roomId") String roomId, @PathVariable("username") String username, @RequestBody String password){

        UserDto userDto = userService.getUserDtoByUsername(username);
        RoomDto roomDto = roomService.getRoomDtoById(roomId);
        if(userDto==null || roomDto==null){
            return new ResponseEntity<String>("Invalid data",HttpStatus.BAD_REQUEST);
        }

        if(roomDto.getPrivacy()==RoomConstants.PRIVACY_PRIVATE && !roomDto.getPassword().equals(password)){
            return new ResponseEntity<String>("Incorrect credentials",HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        Room room = roomService.joinRoom(roomId, username);
        if(room!=null) return new ResponseEntity<>(username + " added to " + roomId,HttpStatus.OK);

        return new ResponseEntity<String>("Some Error Occurred",HttpStatus.BAD_REQUEST);
    }
    
}
