package com.follansbee.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityDetail {

	private String symbol;
	private String latestPrice;
	private String companyName;
	private String previousClose;
	private String open;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLatestPrice() {
		return latestPrice;
	}
	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(String previousClose) {
		this.previousClose = previousClose;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	@Override
	public String toString() {
		return "SecurityDetail [symbol=" + symbol + ", latestPrice=" + latestPrice + ", companyName=" + companyName
				+ ", previousClose=" + previousClose + ", open=" + open + "]";
	}
	
	
}
