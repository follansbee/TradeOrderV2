package com.follansbee.portfolio.repository;

import com.follansbee.portfolio.models.FundId;
import com.follansbee.portfolio.models.FundSummary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FundSummaryRepository extends CrudRepository<FundSummary, FundId> {
    List<FundSummary> findByUserId(String id);
}
