package com.follansbee.portfolio.models;



import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class TradeOrder {

    public TradeOrder(Long id,  String side, String security, String fund, Long quantity, Double purchasePrice, Double currentPrice, Double purchaseValue, Double currentValue, Double percentChange, LocalDateTime dateAdded, String employeeId, Long groupId) {
        this.id = id;
        this.side = side;
        this.security = security;
        this.fund = fund;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.purchaseValue = purchaseValue;
        this.currentValue = currentValue;
        this.percentChange = percentChange;
        this.dateAdded = dateAdded;
        this.employeeId = employeeId;
        this.groupId = groupId;
    }

    public TradeOrder(Long id, String side, String security, String fund, Long quantity, Double purchasePrice, Double purchaseValue, LocalDateTime dateAdded, LocalDateTime dateModified, String employeeId, Long groupId) {
        this.id = id;
        this.side = side;
        this.security = security;
        this.fund = fund;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseValue = purchaseValue;
        this.dateAdded = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.employeeId = employeeId;
        this.groupId = groupId;
    }
    public TradeOrder(Long id, String side, String security, String fund, Long quantity,  LocalDateTime dateAdded, LocalDateTime dateModified, String employeeId, Long groupId) {
        this.id = id;
        this.side = side;
        this.security = security;
        this.fund = fund;
        this.quantity = quantity;
        this.dateAdded = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.employeeId = employeeId;
        this.groupId = groupId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String side;
    private String security;
    private String fund;
    private Long quantity;
    private Double purchasePrice;
    private Double currentPrice;
    private Double purchaseValue;
    private Double currentValue;
    private Double percentChange;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;


    @Column(name = "employeeId")
    private String employeeId;

    @Column(name="groupId")
    private Long groupId;


    public TradeOrder() { //Constructor
        this.dateAdded= LocalDateTime.now();
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(Double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime date) {
        this.dateAdded = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "TradeOrder{" +
                "id='" + id + '\'' +
                ", side='" + side + '\'' +
                ", security='" + security + '\'' +
                ", fund='" + fund + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", currentPrice=" + currentPrice +
                ", purchaseValue=" + purchaseValue +
                ", currentValue=" + currentValue +
                ", percentChange=" + percentChange +
                ", dateAdded=" + dateAdded +
                ", employeeId='" + employeeId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}

