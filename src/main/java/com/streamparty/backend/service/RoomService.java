package com.streamparty.backend.service;

import java.util.HashSet;

import com.streamparty.backend.constants.RoomConstants;
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

    public Room getRoomById(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public Room getRoomByRoomName(String roomName){
        return roomRepository.findByRoomName(roomName);
    }

    public Room createRoom(String roomName, User user, String privacy) {
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
