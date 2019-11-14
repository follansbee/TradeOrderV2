package com.follansbee.portfolio.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "SecuritySummary")
@Table(name = "security_summary")
public class SecuritySummary {

    @EmbeddedId
    private SecurityId id;
    private String userId = "";
    private double totalValue =0;
    private double totalNumOfShares =0;
    private long   totalNumOfOrders = 0;
    private double avgPrice=0;
    private String combinedOrders;

    public SecuritySummary(){};

    public SecuritySummary(SecurityId id, String userId, double totalValue, double totalNumOfShares, long totalNumOfOrders, double avgPrice, String combinedOrders) {
        this.id = id;
        this.userId = userId;
        this.totalValue = totalValue;
        this.totalNumOfShares = totalNumOfShares;
        this.totalNumOfOrders = totalNumOfOrders;
        this.avgPrice = avgPrice;
        this.combinedOrders = combinedOrders;
    }

    public SecurityId getId() {
        return id;
    }

    public void setId(SecurityId id) {
        this.id = id;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getTotalNumOfShares() {
        return totalNumOfShares;
    }

    public void setTotalNumOfShares(double totalNumOfShares) {
        this.totalNumOfShares = totalNumOfShares;
    }



    public long getTotalNumOfOrders() {
        return totalNumOfOrders;
    }

    public void setTotalNumOfOrders(long totalNumOfOrders) {
        this.totalNumOfOrders = totalNumOfOrders;
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
                ", totalAmount=" + totalValue +
                ", totalQty=" + totalNumOfShares +
                ", avgPrice=" + avgPrice +
                ", combinedOrders='" + combinedOrders + '\'' +
                '}';
    }
}
