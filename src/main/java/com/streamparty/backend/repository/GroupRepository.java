package com.streamparty.backend.repository;

import com.streamparty.backend.model.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,String> {

    public Group findByGroupId(String groupId);    

    public Group findByGroupName(String groupName);
}
