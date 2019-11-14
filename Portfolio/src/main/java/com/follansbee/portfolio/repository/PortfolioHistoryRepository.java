package com.follansbee.portfolio.repository;

import com.follansbee.portfolio.models.PortfolioHistory;
import com.follansbee.portfolio.models.PortfolioHistoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioHistoryRepository extends CrudRepository<PortfolioHistory, PortfolioHistoryId> {
    List<PortfolioHistory> findByUserId(String userId);
}
