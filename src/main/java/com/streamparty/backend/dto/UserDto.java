package com.streamparty.backend.dto;

import com.streamparty.backend.model.User;

public class UserDto {
    private String username;
    private String password;
    private String image;

    public UserDto(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.image = user.getImage();
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
