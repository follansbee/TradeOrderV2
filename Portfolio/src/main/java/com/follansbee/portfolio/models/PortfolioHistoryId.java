package com.follansbee.portfolio.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PortfolioHistoryId implements Serializable {

    @Column(name = "date")
    private String date;

    @Column(name = "employeeId")
    private String employeeId;

    public PortfolioHistoryId() {
    }

    public PortfolioHistoryId(String date, String employeeId) {
        this.date = date;
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
