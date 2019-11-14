package com.follansbee.portfolio.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.follansbee.portfolio.models.Users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String groupName;

    @Enumerated(EnumType.STRING)
    private GroupRole role;

    @ManyToMany(mappedBy = "groupList")
    @JsonIgnoreProperties("groupList")
    private Set<Users> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
