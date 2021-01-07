package com.streamparty.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ChatMessage implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_username", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User sender;

    private Date timeStamp;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Room room;

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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ChatMessage(String message, User sender, Room room, String type) {
        this.message = message;
        this.sender = sender;
        this.room = room;
        this.type = type;

        Date currDateTime = Calendar.getInstance().getTime();
        this.timeStamp = currDateTime;
        
    }  
    
    public ChatMessage(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
