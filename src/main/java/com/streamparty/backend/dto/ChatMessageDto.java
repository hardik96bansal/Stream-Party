package com.streamparty.backend.dto;

import java.util.Date;

import com.streamparty.backend.model.ChatMessage;

public class ChatMessageDto {
    private long id;
    private String message;
    private UserDto sender;
    private Date timeStamp;
    private String type;

    public ChatMessageDto(ChatMessage chatMessage){
        this.id = chatMessage.getId();
        this.message = chatMessage.getMessage();
        this.sender = new UserDto(chatMessage.getSender());
        this.timeStamp = chatMessage.getTimeStamp();
        this.type = chatMessage.getType();        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    
}
