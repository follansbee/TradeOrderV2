package com.follansbee.portfolio.services;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.follansbee.portfolio.models.*;
import com.follansbee.portfolio.repository.FundSummaryRepository;
import com.follansbee.portfolio.repository.SecuritySummaryRepository;
import com.follansbee.portfolio.repository.SummaryRepository;
import com.follansbee.portfolio.repository.TradeOrderRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;


import com.follansbee.portfolio.models.Summary;
import com.follansbee.portfolio.models.SummaryAnalysis;
import com.follansbee.portfolio.models.TradeOrder;

@Service
public class TradeOrderSummaryService {

	public TradeOrderSummaryService(TradeOrderRepository repo, SummaryRepository summaryRepository, SecuritySummaryRepository securityRepository, FundSummaryRepository fundRepository, UserService userService) {
		this.repo = repo;
		this.summaryRepository = summaryRepository;
		this.securityRepository = securityRepository;
		this.fundRepository = fundRepository;
		this.userService = userService;
	}

	TradeOrderRepository repo;
	SummaryRepository summaryRepository;
	SecuritySummaryRepository securityRepository;
	FundSummaryRepository fundRepository;
	UserService userService;

	private boolean showDebugMessages;

	public void updateAllSummaries() {
		List<Users> userList = userService.getUsers();

		//userList.forEach(users -> updateAllTradeOrders((ArrayList<TradeOrder>) repo.findByEmployeeId(users.getEmployeeId())));
		for (Users u: userList) {
			List<TradeOrder> to = (List<TradeOrder>) repo.findByEmployeeId(u.getEmployeeId());
			if(to.size()==0) continue;
			updateAllTradeOrders((ArrayList<TradeOrder>) to, u.getEmployeeId());
		}

	}

	public void updateAllTradeOrders(ArrayList<TradeOrder> tradeOrders, String employeeId) {

		//Build list of Securities
		buildDataList(tradeOrders);

		Summary summary = getSummaryStats(tradeOrders,true);
		summary.setEmployeeId(employeeId);
		summaryRepository.save(summary);

		//We need to populate the SummaryAnalysis container with the Summaries for all Sides/securities and funds
		//summarizeSides(employeeId);
		summarizeSecurities(employeeId);
		summarizeFunds(employeeId);
	}

	private Summary getSummaryStats(ArrayList<TradeOrder> tradeOrders) {
		return getSummaryStats(tradeOrders, false);
	}

	//*This is re-usable, we process all the summaries the same, just limit the set of Data being passed in
	public Summary getSummaryStats(ArrayList<TradeOrder> tradeOrders, boolean calcWeightedAvg) {

		Summary summary = new Summary();
		if(tradeOrders.size()==0) {
			return summary;
		}
		Long orderQty = tradeOrders.stream()
				.filter(tradeOrder -> tradeOrder.getSide().equals("BUY"))
                .mapToLong((x) -> x.getQuantity())
                .sum();

		Double orderTotal = tradeOrders.stream()
				.filter(tradeOrder -> tradeOrder.getSide().equals("BUY"))
				.mapToDouble((x) -> x.getCurrentValue())
				.sum();

		//double averagePrice = orderTotal/tradeOrders.size();
		double averagePrice;
		if(orderQty!=0) {
			averagePrice = orderTotal / orderQty;
		} else {
			averagePrice = 0;
		}

		summary.setAvgPrice(Double.parseDouble(new DecimalFormat("#0.00").format(averagePrice)));
		summary.setTotalNumOfOrders(tradeOrders.size());
		summary.setTotalNumOfShares(orderQty);
		summary.setTotalValue(orderTotal);
		summary.setCombinableOrderDetails(getCombinedOrders(tradeOrders));

		if(calcWeightedAvg)  {
			summary.setWeightedAvg(Double.parseDouble(new DecimalFormat("#0.00").format(calculateWeightedAvg(tradeOrders))));
		}
		return summary;

	}

//////////PRIVATE METHODS///////////////
	//********************
	//Build a String that lists the Combined Orders
	//********************
	private String getCombinedOrders(ArrayList<TradeOrder> tradeOrders) {
		Map<String, Integer> counts = new ConcurrentHashMap<String, Integer>();

		for (TradeOrder trade : tradeOrders) {
			String fund = trade.getSide() + "+" + trade.getSecurity() + "+" + trade.getFund();
		    if (counts.containsKey(fund)) {
		        counts.put(fund, counts.get(fund) + 1);
		    } else {
		        counts.put(fund, 1);
		    }
		}

		int countCombined=0;
		StringBuilder combinedDetails= new StringBuilder();

		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			int value = entry.getValue();
			if(value>1) {
				countCombined++;
				combinedDetails.append("(" + entry.getKey() + ")");
			}
		}

		return countCombined==0 ? "None" : countCombined + " " + combinedDetails.toString();
	}

	////SIDES
//	private void summarizeSides(String employeeId) {
//		//For each "Side", we need to run getSummaryStats with a filtered list then add the summary to the Side hashmap in the SummaryAnalysis
//
//		ConcurrentHashMap<String,Summary> sideSummary = new ConcurrentHashMap<String,Summary>();
//		String sides[] = {"Buy", "Sell"};
//		for(String side: sides) {
//			Summary holdSummary = new Summary();
//			holdSummary = getSummaryStats((ArrayList<TradeOrder>) repo.findByEmployeeIdAndSide(employeeId, side));
//			sideSummary.put(side, holdSummary);
//		}
//
//		SummaryAnalysis.setSideAnalysis(sideSummary);
//	}



	//SECURITIES
	private void summarizeSecurities(String employeeId) {
		//For each "Security"
		for(String security: TradeOrderData.securityMasterList) {
			Summary holdSummary = new Summary();
			holdSummary = getSummaryStats((ArrayList<TradeOrder>) repo.findByEmployeeIdAndSecurity(employeeId, security)); //do I need to pass in the summary object?
			if(holdSummary.getTotalNumOfOrders()>0) {
				SecuritySummary ss = populateSecuritySummary(holdSummary, employeeId, security);
				securityRepository.save(ss);
			}
		}
	}

	//FUNDS
	private void summarizeFunds(String employeeId) {

		for(String fund: TradeOrderData.fundMasterList) {
			Summary holdSummary = new Summary();
			holdSummary = getSummaryStats((ArrayList<TradeOrder>) repo.findByEmployeeIdAndFund(employeeId, fund));
			if(holdSummary.getTotalNumOfOrders()>0) {
				FundSummary fs = populateFundSummary(holdSummary, employeeId, fund);
				fundRepository.save(fs);
			}
		}
	}

	//WEIGHTED AVG
	private double calculateWeightedAvg(ArrayList<TradeOrder> tradeOrders) {

		long qtySumTotal=0;
		double totalQtyPrice = 0;

		for(String security: TradeOrderData.securityMasterList) {
			long securitySum=0;  //for each security, the total number of units purchased
			double price = 0; //this is constant in this scenario

			for(TradeOrder t: tradeOrders) {
				if(t.getSecurity().equals(security)) {
					price=t.getCurrentPrice();
					securitySum += t.getQuantity();
				}
			}
			totalQtyPrice+= (price*securitySum); //for this security we multiple the total number of units by the price
			qtySumTotal+=securitySum; //we add the number of units for this security into the total units

		}

		if(showDebugMessages) System.out.println("Weighted Avg -- " + totalQtyPrice/qtySumTotal);

		if (qtySumTotal==0) return 0; //this shouldn't happen but we don't want math exceptions
		//we divide the sum of all the (price*securitySum) by the total number of units
		return totalQtyPrice/qtySumTotal;

	}

	private SecuritySummary populateSecuritySummary(Summary summary, String employeeId, String security) {

		SecurityId securityId = new SecurityId();
		securityId.setSymbol(security);
		securityId.setEmployeeId(employeeId);
		SecuritySummary ss = new SecuritySummary(securityId, employeeId,  summary.getTotalValue(),  summary.getTotalNumOfShares(), summary.getTotalNumOfOrders(), summary.getAvgPrice(), summary.getCombinableOrderDetails());

		return ss;
	}

	private FundSummary populateFundSummary(Summary summary, String employeeId, String fund) {

		FundId fundId = new FundId();
		fundId.setFund(fund);
		fundId.setEmployeeId(employeeId);
		FundSummary f = new FundSummary(fundId, employeeId, summary.getTotalNumOfShares(), summary.getTotalNumOfOrders(), summary.getTotalValue() , summary.getAvgPrice(), summary.getCombinableOrderDetails());

		return f;
	}

	private void buildDataList(ArrayList<TradeOrder> tradeOrders) {
		TradeOrderData.securityMasterList = null;
		TradeOrderData.fundMasterList = null;

		HashSet<String> funds = new HashSet<String>();
		HashSet<String> securities = new HashSet<String>();
		for(TradeOrder t: tradeOrders) {
			securities.add(t.getSecurity());
			funds.add(t.getFund());
		}

		TradeOrderData.securityMasterList = securities;
		TradeOrderData.fundMasterList = funds;
	}
}