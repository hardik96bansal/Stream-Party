package com.streamparty.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
public class User extends AuditModel{
    @Id
    @Access(AccessType.PROPERTY)
    private String username;
    private String password;
    private boolean isActive;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user_room",
            joinColumns = { @JoinColumn(name = "user_username") },
            inverseJoinColumns = { @JoinColumn(name = "room_id") })
    @JsonIgnore
    private List<Room> rooms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String username, String password, boolean isActive, String image) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.image = image;
        this.rooms = new ArrayList<Room>();
    }

    public User() {
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        if(this.rooms == null) this.rooms = new ArrayList<Room>();
        this.rooms.add(room);
    }

    public void removeRoom(Room room){
        if(this.rooms!=null) this.rooms.remove(room);
    }

}
