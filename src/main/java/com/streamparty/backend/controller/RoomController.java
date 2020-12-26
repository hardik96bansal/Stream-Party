package com.streamparty.backend.controller;

import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.service.RoomService;
import com.streamparty.backend.service.UserService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @GetMapping("/room/{roomId}")
    public Room getRoomById(@PathVariable("roomId") String roomId){
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/room")
    public ResponseEntity<String> createRoom(@RequestBody Room room){
        Room roomExists = roomService.getRoomByRoomName(room.getRoomName());
        
        if(roomExists!=null){
            return new ResponseEntity<String>("Room name already taken",HttpStatus.BAD_REQUEST);
        }

        if(room.getRoomName()==null || room.getAdmin()==null || room.getPrivacy()==null){
            return new ResponseEntity<String>("Invalid data",HttpStatus.BAD_REQUEST);
        }

        roomService.createRoom(room.getRoomName(), room.getAdmin(), room.getPrivacy());
        return new ResponseEntity<>("Room Created",HttpStatus.OK);
    }

    @GetMapping("room/add/{roomId}/{approver}/{username}")
    public ResponseEntity<String> addMemberToRoom(@PathVariable("roomId") String roomId,
                                 @PathVariable("approver") String approver, 
                                 @PathVariable("username") String username){

        User approverUser = userService.getUserByUsername(approver);
        User addUser = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);

        if(approverUser==null || addUser==null || room == null){
            return new ResponseEntity<>("Invalid values provided",HttpStatus.BAD_REQUEST);
        }

        if(room.getAdmin()!=approverUser){
            return new ResponseEntity<>("Only admin can add",HttpStatus.UNAUTHORIZED);
        }

        roomService.addMember(roomId, addUser);  
        room = roomService.getRoomById(roomId);        
        userService.addUserToRoom(username, room);
        
        return new ResponseEntity<>("User added to the room",HttpStatus.OK);  
    }

    @GetMapping("room/remove/{roomId}/{approver}/{username}")
    public ResponseEntity<String> removeMemberFromRoom(@PathVariable("roomId") String roomId,
                                 @PathVariable("approver") String approver, 
                                 @PathVariable("username") String username){

        User approverUser = userService.getUserByUsername(approver);
        User removeUser = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);

        if(approverUser==null || removeUser==null || room == null){
            return new ResponseEntity<>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        if(room.getAdmin()==approverUser || removeUser==approverUser){
            roomService.removeMember(roomId, removeUser);
            userService.removeUserFromRoom(username, room);
            return new ResponseEntity<>("User removed from room",HttpStatus.OK);
        }

        return new ResponseEntity<>("Authorization error",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("room/connect/{roomId}/{username}")
    public ResponseEntity<String> connectToRoom(@PathVariable String roomId, @PathVariable String username){
        User user = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);

        if(user==null || room == null){
            return new ResponseEntity<String>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        roomService.connectToRoom(roomId, user);

        return new ResponseEntity<>("User connected to the room", HttpStatus.OK);
    }

    @GetMapping("room/disconnect/{roomId}/{username}")
    public ResponseEntity<String> disconnectFromRoom(@PathVariable String roomId, @PathVariable String username){
        User user = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);

        if(user==null || room == null){
            return new ResponseEntity<String>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        roomService.disconnetFromRoom(roomId, user);

        return new ResponseEntity<>("User disconnected from the room", HttpStatus.OK);
    }

    
    
}
