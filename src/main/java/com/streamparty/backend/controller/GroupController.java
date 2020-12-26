package com.streamparty.backend.controller;

import com.streamparty.backend.model.Group;

import org.springframework.web.bind.annotation.GetMapping;

public class GroupController {

    @GetMapping("/group/{groupId}")
    public Group getGroupById(){
        return null;
    }
    
}
