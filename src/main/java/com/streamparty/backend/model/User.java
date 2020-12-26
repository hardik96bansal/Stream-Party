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
    private List<Group> groups;

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
        this.groups = new ArrayList<Group>();
    }

    public User() {
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        if(this.groups == null) this.groups = new ArrayList<Group>();
        this.groups.add(group);
    }

    public void removeGroup(Group group){
        if(this.groups!=null) this.groups.remove(group);
    }

}
