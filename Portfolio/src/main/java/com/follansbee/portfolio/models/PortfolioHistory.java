package com.follansbee.portfolio.models;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="portfolioHistory")
public class PortfolioHistory {

    @Id
    private PortfolioHistoryId id;
    private String userId;
    private long totalNumOfOrders =0;
    private long totalNumOfShares = 0;
    private double totalValue=0;
    private double avgPrice=0.0;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    public PortfolioHistory(){};

    public PortfolioHistory(PortfolioHistoryId id, String userId, long totalNumOfOrders, long totalNumOfShares, double totalValue, double avgPrice) {
        this.id = id;
        this.userId = userId;
        this.totalNumOfOrders = totalNumOfOrders;
        this.totalNumOfShares = totalNumOfShares;
        this.totalValue = totalValue;
        this.avgPrice = avgPrice;
        this.updateDateTime = LocalDateTime.now();
    }

    public PortfolioHistoryId getId() {
        return id;
    }

    public void setId(PortfolioHistoryId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTotalNumOfOrders() {
        return totalNumOfOrders;
    }

    public void setTotalNumOfOrders(long totalNumOfOrders) {
        this.totalNumOfOrders = totalNumOfOrders;
    }

    public long getTotalNumOfShares() {
        return totalNumOfShares;
    }

    public void setTotalNumOfShares(long totalNumOfShares) {
        this.totalNumOfShares = totalNumOfShares;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
