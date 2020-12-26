package com.streamparty.backend.service;

import java.util.HashSet;
import java.util.Set;

import com.streamparty.backend.constants.GroupConstants;
import com.streamparty.backend.model.Group;
import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Group getGroupById(String groupId) {
        return groupRepository.findByGroupId(groupId);
    }

    public void createGroup(String groupName, User user, String privacy) {
        Group newGroup = new Group(groupName, user, privacy);
        groupRepository.save(newGroup);
    }

    public void addMember(String groupId, User user) {
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup!=null){
            if (currGroup.getMembers() == null)
                currGroup.setMembers(new HashSet<User>());
            currGroup.addMember(user);
            groupRepository.save(currGroup);
        }        
    }

    public void removeMember(String groupId, User user) {
        Group currGroup = groupRepository.findByGroupId(groupId);
        if (currGroup != null) {
            currGroup.removeMember(user);
            groupRepository.save(currGroup);
        }
    }

    public void connectToGroup(String groupId, User user){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
           currGroup.addToConnectedMembers(user);
           groupRepository.save(currGroup);
        }
    }

    public void disconnetFromGroup(String groupId, User user){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
           currGroup.removeFromConnectedMembers(user);
           groupRepository.save(currGroup);
        }
    }

    public void makeGroupPublic(String groupId){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
            currGroup.setPrivacy(GroupConstants.PRIVACY_PUBLIC);
            groupRepository.save(currGroup);
        }
    }

    public void makeGroupPrivate(String groupId){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
            currGroup.setPrivacy(GroupConstants.PRIVACY_PRIVATE);
            groupRepository.save(currGroup);
        }
    }

    
}
