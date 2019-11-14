package com.follansbee.portfolio.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Component
@Embeddable
public class FundId implements Serializable {

    @Column(name = "fund")
    private String fund;

    @Column(name = "employeeId")
    private String employeeId;

    public FundId() {
    }

    public FundId(String fund, String employeeId) {
        this.fund = fund;
        this.employeeId = employeeId;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
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