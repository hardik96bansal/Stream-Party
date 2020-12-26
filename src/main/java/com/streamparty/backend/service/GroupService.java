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

    public Group getGroupById(long groupId) {
        return groupRepository.findByGroupId(groupId);
    }

    public void createGroup(String groupName, User user, String privacy) {
        Group newGroup = new Group(groupName, user, privacy);
        groupRepository.save(newGroup);
    }

    public void addMember(long groupId, User user) {
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup!=null){
            if (currGroup.getMembers() == null)
                currGroup.setMembers(new HashSet<User>());
            currGroup.addMember(user);
            groupRepository.save(currGroup);
        }        
    }

    public void removeMember(long groupId, User user) {
        Group currGroup = groupRepository.findByGroupId(groupId);
        if (currGroup != null) {
            Set<User> groupMembers = currGroup.getMembers();
            groupMembers.remove(user);
            currGroup.setMembers(groupMembers);
            groupRepository.save(currGroup);
        }
    }

    public void connectToGroup(long groupId, User user){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
           currGroup.addToConnectedMembers(user);
        }
    }

    public void disconnetFromGroup(long groupId, User user){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
           currGroup.removeFromConnectedMembers(user);
        }
    }

    public void makeGroupPublic(long groupId){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
            currGroup.setPrivacy(GroupConstants.PRIVACY_PUBLIC);
            groupRepository.save(currGroup);
        }
    }

    public void makeGroupPrivate(long groupId){
        Group currGroup = groupRepository.findByGroupId(groupId);
        if(currGroup != null){
            currGroup.setPrivacy(GroupConstants.PRIVACY_PRIVATE);
            groupRepository.save(currGroup);
        }
    }

    
}
