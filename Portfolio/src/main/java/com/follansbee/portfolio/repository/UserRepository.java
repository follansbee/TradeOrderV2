package com.follansbee.portfolio.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.follansbee.portfolio.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, String> {


}
