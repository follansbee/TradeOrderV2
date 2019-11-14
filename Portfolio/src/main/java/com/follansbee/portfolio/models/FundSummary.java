package com.follansbee.portfolio.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "FundSummary")
@Table(name = "fund_summary")
public class FundSummary {

    @EmbeddedId
    private FundId id;
    String userId = "";
    double totalNumOfShares =0;
    double totalNumOfOrders = 0;
    double totalValue =0;
    double avgPrice=0;
    String combinedOrders;

    public FundSummary() {
    }

    public FundSummary(FundId id, String userId, double totalNumOfShares, double totalNumOfOrders, double totalValue, double avgPrice, String combinedOrders) {
        this.id = id;
        this.userId = userId;
        this.totalNumOfShares = totalNumOfShares;
        this.totalNumOfOrders = totalNumOfOrders;
        this.totalValue = totalValue;
        this.avgPrice = avgPrice;
        this.combinedOrders = combinedOrders;
    }

    public FundId getId() {
        return id;
    }

    public void setId(FundId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalNumOfShares() {
        return totalNumOfShares;
    }

    public void setTotalNumOfShares(double totalNumOfShares) {
        this.totalNumOfShares = totalNumOfShares;
    }

    public double getTotalNumOfOrders() {
        return totalNumOfOrders;
    }

    public void setTotalNumOfOrders(double totalNumOfOrders) {
        this.totalNumOfOrders = totalNumOfOrders;
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

    public String getCombinedOrders() {
        return combinedOrders;
    }

    public void setCombinedOrders(String combinedOrders) {
        this.combinedOrders = combinedOrders;
    }

    @Override
    public String toString() {
        return "SecuritySummary{" +
                "id=" + id +
                "userId=" + userId +
                ", totalAmount=" + totalNumOfShares +
                ", totalQty=" + totalValue +
                ", avgPrice=" + avgPrice +
                ", combinedOrders='" + combinedOrders + '\'' +
                '}';
    }
}
