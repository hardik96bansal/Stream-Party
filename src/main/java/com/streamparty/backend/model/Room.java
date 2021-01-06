package com.streamparty.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.streamparty.backend.constants.RoomConstants;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
public class Room extends AuditModel{

    
    @Id
    @Column(name = "id")
    private String roomId;

    private String roomName;
    private String privacy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_username", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User admin;
    private boolean isActive;
    private Date lastActive;
    
    //@ElementCollection
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "rooms")
    @JsonIgnore
    private Set<User> members;

    private String password;
    

    public void addMember(User user){
        if(this.members==null) members = new HashSet<User>();
        members.add(user);
    }

    public void removeMember(User user){
        if(this.members!=null){
            members.remove(user);
        }
    }

    public Room(String roomId, String roomName, User admin, String privacy) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.admin = admin;

        if(privacy.equals(RoomConstants.PRIVACY_PUBLIC)) this.privacy = privacy;
        else{
            this.privacy = RoomConstants.PRIVACY_PRIVATE;
            this.password = privacy;
        }
        this.isActive = true;
        this.members = new HashSet<User>();
    }

    public Room() {
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
