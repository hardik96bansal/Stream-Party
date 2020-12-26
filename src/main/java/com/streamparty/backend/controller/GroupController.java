package com.streamparty.backend.controller;

import com.streamparty.backend.model.Group;
import com.streamparty.backend.model.User;
import com.streamparty.backend.service.GroupService;
import com.streamparty.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @GetMapping("/group/{groupId}")
    public Group getGroupById(@PathVariable("groupId") String groupId){
        return groupService.getGroupById(groupId);
    }

    @GetMapping("group/add/{groupId}/{approver}/{username}")
    public ResponseEntity<String> addMemberToGroup(@PathVariable("groupId") String groupId,
                                 @PathVariable("approver") String approver, 
                                 @PathVariable("username") String username){

        User approverUser = userService.getUserByUsername(approver);
        User addUser = userService.getUserByUsername(username);
        Group group = groupService.getGroupById(groupId);

        if(approverUser==null || addUser==null || group == null){
            return new ResponseEntity<>("Invalid values provided",HttpStatus.BAD_REQUEST);
        }

        if(group.getAdmin()!=approverUser){
            return new ResponseEntity<>("Only admin can add",HttpStatus.UNAUTHORIZED);
        }

        groupService.addMember(groupId, addUser);  
        group = groupService.getGroupById(groupId);        
        userService.addUserToGroup(username, group);
        
        return new ResponseEntity<>("User added to the group",HttpStatus.OK);  
    }

    @GetMapping("group/remove/{groupId}/{approver}/{username}")
    public ResponseEntity<String> removeMemberFromGroup(@PathVariable("groupId") String groupId,
                                 @PathVariable("approver") String approver, 
                                 @PathVariable("username") String username){

        User approverUser = userService.getUserByUsername(approver);
        User removeUser = userService.getUserByUsername(username);
        Group group = groupService.getGroupById(groupId);

        if(approverUser==null || removeUser==null || group == null){
            return new ResponseEntity<>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        if(group.getAdmin()==approverUser || removeUser==approverUser){
            groupService.removeMember(groupId, removeUser);
            userService.removeUserFromGroup(username, group);
            return new ResponseEntity<>("User removed from group",HttpStatus.OK);
        }

        return new ResponseEntity<>("Authorization error",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("group/connect/{groupId}/{username}")
    public ResponseEntity<String> connectToGroup(@PathVariable String groupId, @PathVariable String username){
        User user = userService.getUserByUsername(username);
        Group group = groupService.getGroupById(groupId);

        if(user==null || group == null){
            return new ResponseEntity<String>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        groupService.connectToGroup(groupId, user);

        return new ResponseEntity<>("User connected to the group", HttpStatus.OK);
    }

    @GetMapping("group/disconnect/{groupId}/{username}")
    public ResponseEntity<String> disconnectFromGroup(@PathVariable String groupId, @PathVariable String username){
        User user = userService.getUserByUsername(username);
        Group group = groupService.getGroupById(groupId);

        if(user==null || group == null){
            return new ResponseEntity<String>("Invalid values provided", HttpStatus.BAD_REQUEST);
        }

        groupService.disconnetFromGroup(groupId, user);

        return new ResponseEntity<>("User disconnected from the group", HttpStatus.OK);
    }

    
    
}
