package com.follansbee.portfolio.services;

import com.follansbee.portfolio.models.UserGroup;
import com.follansbee.portfolio.models.Users;
import com.follansbee.portfolio.repository.GroupRepository;
import com.follansbee.portfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public List<Users> getUsers () {
        return (List<Users>) userRepository.findAll();
    }

    public Optional<Users> getUser(String employeeId) {
        return userRepository.findById(employeeId);
    }

    public void delete(String employeeId) {
        userRepository.deleteById(employeeId);
    }

    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    public void addUserToGroup(Long groupId, String employeeId) {

        Optional<Users> user = userRepository.findById(employeeId);
        Optional<UserGroup> group = groupRepository.findById(groupId);
        user.get().addGroup(group.get());
        userRepository.save(user.get());
    }
}
