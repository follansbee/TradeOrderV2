package com.follansbee.portfolio.services;

import com.follansbee.portfolio.models.UserGroup;
import com.follansbee.portfolio.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public List<UserGroup> getGroups () {
        return (List<UserGroup>) groupRepository.findAll();
    }

    public Optional<UserGroup> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public UserGroup addGroup(UserGroup group) {
        return groupRepository.save(group);
    }
}
