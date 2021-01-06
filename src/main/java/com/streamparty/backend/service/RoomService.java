package com.streamparty.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.streamparty.backend.constants.RoomConstants;
import com.streamparty.backend.dto.RoomDto;
import com.streamparty.backend.dto.UserDto;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.RoomRepository;
import com.streamparty.backend.repository.UserRepository;
import com.streamparty.backend.utils.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public Room getRoomById(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public RoomDto getRoomDtoById(String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null) return null;
        return new RoomDto(room);
    }

    public List<UserDto> getRoomMembers(String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room==null || room.getMembers()==null) return null;
        return room.getMembers().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }

    public UserDto getRoomAdmin(String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room==null || room.getAdmin()==null) return null;
        return new UserDto(room.getAdmin());
    }

    public Room createRoom(String roomName, String username, String privacy) {
        User user = userService.getUserByUsername(username);

        RandomString randomString = new RandomString(8);
        String roomId = randomString.nextString();

        while(roomRepository.findByRoomId(roomId)!=null){
            roomId = randomString.nextString();
        }

        Room newRoom = new Room(roomId, roomName, user, privacy);

        user.getRooms().add(newRoom);
        newRoom.getMembers().add(user);

        userRepository.save(user);
        
        return newRoom;
    }

    public Room joinRoom(String roomId, String username) {
        Room room = roomRepository.findByRoomId(roomId);
        User user = userRepository.findByUsername(username);

        user.getRooms().add(room);
        room.getMembers().add(user);

        userRepository.save(user);
        
        return room;
    }

    public void makeRoomPublic(String roomId){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
            currRoom.setPrivacy(RoomConstants.PRIVACY_PUBLIC);
            roomRepository.save(currRoom);
        }
    }

    public void makeRoomPrivate(String roomId, String password){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
            currRoom.setPrivacy(RoomConstants.PRIVACY_PRIVATE);
            currRoom.setPassword(password);
            roomRepository.save(currRoom);
        }
    }

    
}
