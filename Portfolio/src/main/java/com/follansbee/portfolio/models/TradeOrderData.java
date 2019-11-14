package com.follansbee.portfolio.models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class TradeOrderData {  //used to hold lists of Securities and Funds
	
	public static HashSet<String> fundMasterList = new HashSet<String>();
	public static HashSet<String> securityMasterList = new HashSet<String>();

}
