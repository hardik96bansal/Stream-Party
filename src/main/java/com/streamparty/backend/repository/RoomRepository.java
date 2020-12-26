package com.streamparty.backend.repository;

import com.streamparty.backend.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {

    public Room findByRoomId(String roomId);    

    public Room findByRoomName(String roomName);
}
