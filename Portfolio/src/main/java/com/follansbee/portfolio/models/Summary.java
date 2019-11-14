package com.follansbee.portfolio.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="trade_order_summary")
public class Summary {

	@Id
	private String employeeId;
	private long totalNumOfOrders =0;
	private long totalNumOfShares = 0;
	private double totalValue=0;
	private double avgPrice=0.0;
	private double weightedAvg=0.0; //Only to be populated when then the request is for 'total summary'

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	private String combinableOrderDetails;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public void setAvgPrice(double d) {
		this.avgPrice = d;
	}
	public String getCombinableOrderDetails() {
		return combinableOrderDetails;
	}
	public void setCombinableOrderDetails(String combinableOrderDetails) {
		this.combinableOrderDetails = combinableOrderDetails;
	}
	public double getWeightedAvg() {
		return weightedAvg;
	}
	public void setWeightedAvg(double weightedAvg) {
		this.weightedAvg = weightedAvg;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
}
