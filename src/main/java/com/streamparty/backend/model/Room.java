package com.streamparty.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Room implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String roomId;

    private String roomName;
    private String privacy;
    private User admin;
    private boolean isActive;
    private Date lastActive;
    
    @ElementCollection
    private Set<User> members;
    @ElementCollection
    private Set<User> connectedMembers;
    

    public void addMember(User user){
        if(this.members==null) members = new HashSet<User>();
        members.add(user);
    }

    public void removeMember(User user){
        if(this.members!=null){
            members.remove(user);
        }
        if(this.connectedMembers!=null){
            connectedMembers.remove(user);
        }
    }

    public void addToConnectedMembers(User user){
        if(connectedMembers==null) connectedMembers = new HashSet<User>();
        connectedMembers.add(user);
        this.setActive(true);

        Date currDate = Calendar.getInstance().getTime();
        this.setLastActive(currDate);
    }

    public void removeFromConnectedMembers(User user){
        if(connectedMembers!=null){
            connectedMembers.remove(user);
            if(connectedMembers.size()==0){
                this.setActive(false);
            }
        } 
    }

    public Room(String roomName, User admin, String privacy) {
        this.roomName = roomName;
        this.admin = admin;
        this.privacy = privacy;
        this.isActive = true;
        this.members = new HashSet<User>();
        this.connectedMembers = new HashSet<User>();
        members.add(admin);
        connectedMembers.add(admin);
    }

    public Room() {
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }   

    public Set<User> getConnectedMembers() {
        return connectedMembers;
    }

    public void setConnectedMembers(Set<User> connectedMembers) {
        this.connectedMembers = connectedMembers;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
