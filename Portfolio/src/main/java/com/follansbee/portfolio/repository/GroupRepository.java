package com.follansbee.portfolio.repository;

import com.follansbee.portfolio.models.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository <UserGroup,Long> {
}
