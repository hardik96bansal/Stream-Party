package com.streamparty.backend.service;

import java.util.HashSet;
import java.util.Set;

import com.streamparty.backend.constants.RoomConstants;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room getRoomById(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public Room getRoomByRoomName(String roomName){
        return roomRepository.findByRoomName(roomName);
    }

    public void createRoom(String roomName, User user, String privacy) {
        Room newRoom = new Room(roomName, user, privacy);
        roomRepository.save(newRoom);
    }

    public void addMember(String roomId, User user) {
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom!=null){
            if (currRoom.getMembers() == null)
                currRoom.setMembers(new HashSet<User>());
            currRoom.addMember(user);
            roomRepository.save(currRoom);
        }        
    }

    public void removeMember(String roomId, User user) {
        Room currRoom = roomRepository.findByRoomId(roomId);
        if (currRoom != null) {
            currRoom.removeMember(user);
            roomRepository.save(currRoom);
        }
    }

    public void connectToRoom(String roomId, User user){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
           currRoom.addToConnectedMembers(user);
           roomRepository.save(currRoom);
        }
    }

    public void disconnetFromRoom(String roomId, User user){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
           currRoom.removeFromConnectedMembers(user);
           roomRepository.save(currRoom);
        }
    }

    public void makeRoomPublic(String roomId){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
            currRoom.setPrivacy(RoomConstants.PRIVACY_PUBLIC);
            roomRepository.save(currRoom);
        }
    }

    public void makeRoomPrivate(String roomId){
        Room currRoom = roomRepository.findByRoomId(roomId);
        if(currRoom != null){
            currRoom.setPrivacy(RoomConstants.PRIVACY_PRIVATE);
            roomRepository.save(currRoom);
        }
    }

    
}
