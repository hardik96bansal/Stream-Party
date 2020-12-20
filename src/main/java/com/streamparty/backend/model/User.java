package com.streamparty.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sp_users")
public class User {
    @Id
    private String username;
    private String password;
    private boolean isActive;
    private String image;

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
    }

    public User() {
    }
}
