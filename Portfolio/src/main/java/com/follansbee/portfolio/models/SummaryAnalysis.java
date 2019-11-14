package com.follansbee.portfolio.models;

import java.util.concurrent.ConcurrentHashMap;

public  class SummaryAnalysis {

	private static Summary summary;
	
	private static ConcurrentHashMap<String, Summary> fundAnalysis;
	private static ConcurrentHashMap<String, Summary> sideAnalysis;	
	private static ConcurrentHashMap<String, Summary> securityAnalysis;
	
	public static Summary getSummary() {
		return summary;
	}
	public static void setSummary(Summary summary) {
		SummaryAnalysis.summary = summary;
	}
	public static ConcurrentHashMap<String, Summary> getFundAnalysis() {
		return fundAnalysis;
	}
	public static void setFundAnalysis(ConcurrentHashMap<String, Summary> fundAnalysis) {
		SummaryAnalysis.fundAnalysis = fundAnalysis;
	}
	public static ConcurrentHashMap<String, Summary> getSideAnalysis() {
		return sideAnalysis;
	}
	public static void setSideAnalysis(ConcurrentHashMap<String, Summary> sideAnalysis) {
		SummaryAnalysis.sideAnalysis = sideAnalysis;
	}
	public static ConcurrentHashMap<String, Summary> getSecurityAnalysis() {
		return securityAnalysis;
	}
	public static void setSecurityAnalysis(ConcurrentHashMap<String, Summary> securityAnalysis) {
		SummaryAnalysis.securityAnalysis = securityAnalysis;
	}
	
	
}
