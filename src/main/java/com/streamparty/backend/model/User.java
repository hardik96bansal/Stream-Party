package com.streamparty.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
@Table(name = "sp_users")
public class User implements Serializable{
    @Id
    private String username;
    private String password;
    private boolean isActive;
    private String image;

    @ElementCollection
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
