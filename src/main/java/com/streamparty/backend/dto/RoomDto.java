package com.streamparty.backend.dto;

import java.util.Date;

import com.streamparty.backend.model.Room;

public class RoomDto {

    private String roomId;
    private String roomName;
    private String privacy;
    private boolean isActive;
    private Date lastActive;
    private String password;

    public RoomDto(Room room){
        this.roomId = room.getRoomId();
        this.roomName = room.getRoomName();
        this.privacy = room.getPrivacy();
        this.isActive = room.isActive();
        this.lastActive = room.getLastActive();
        this.password = room.getPassword();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
