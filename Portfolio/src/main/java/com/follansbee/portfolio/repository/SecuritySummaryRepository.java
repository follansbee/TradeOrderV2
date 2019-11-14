package com.follansbee.portfolio.repository;

import com.follansbee.portfolio.models.SecurityId;
import com.follansbee.portfolio.models.SecuritySummary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecuritySummaryRepository extends CrudRepository<SecuritySummary, SecurityId> {
    List<SecuritySummary> findByUserId(String id);

}
