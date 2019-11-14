package com.follansbee.portfolio.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Component
@Embeddable
public class SecurityId implements Serializable {

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "employeeId")
    private String employeeId;

    public SecurityId() {
    }

    public SecurityId(String symbol, String employeeId) {
        this.symbol = symbol;
        this.employeeId = employeeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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