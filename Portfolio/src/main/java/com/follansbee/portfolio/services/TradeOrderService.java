package com.follansbee.portfolio.services;


import com.follansbee.portfolio.models.*;
import com.follansbee.portfolio.repository.FundSummaryRepository;
import com.follansbee.portfolio.repository.SecuritySummaryRepository;
import com.follansbee.portfolio.repository.SummaryRepository;
import com.follansbee.portfolio.repository.TradeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TradeOrderService {

    private TradeOrderRepository tradeOrderRepository;
    private SummaryRepository summaryRepository;
    private UtilityService utilityService;
    private UserService userService;
    private SecuritySummaryRepository securitySummaryRepository;
    private FundSummaryRepository fundSummaryRepository;

    @Autowired
    private SecurityId securityId;

    @Autowired
    private FundId fundId;

    public TradeOrderService(TradeOrderRepository tradeOrderRepository, SummaryRepository summaryRepository, SecuritySummaryRepository securitySummaryRepository,  FundSummaryRepository fundSummaryRepository, UtilityService utilityService, UserService userService) {
        this.tradeOrderRepository = tradeOrderRepository;
        this.summaryRepository = summaryRepository;
        this.securitySummaryRepository = securitySummaryRepository;
        this.fundSummaryRepository = fundSummaryRepository;
        this.utilityService = utilityService;
        this.userService = userService;

    }


    public Optional<Summary> getTradeOrderSummaryById(String employeeId){
        return summaryRepository.findById(employeeId);
    }

    public List<Summary> getAllTradeOrderSummary() {
        return (List<Summary>) summaryRepository.findAll();
    }

    public List<SecuritySummary> getSecuritySummary(String employeeId) {
       return  securitySummaryRepository.findByUserId(employeeId);
    }

    public Optional<SecuritySummary> getSecuritySummary(String symbol, String employeeId) {
        securityId.setSymbol(symbol);
        securityId.setEmployeeId(employeeId);
        return  securitySummaryRepository.findById(securityId);
    }

    //Fund
    public List<FundSummary> getFundSummary(String employeeId) {
        return  fundSummaryRepository.findByUserId(employeeId);
    }

    public Optional<FundSummary> getFundSummary(String fund, String employeeId) {
        fundId.setFund(fund);
        fundId.setEmployeeId(employeeId);
        return  fundSummaryRepository.findById(fundId);
    }


    public Optional<TradeOrder> getTradeOrder(long id) {

        Optional<TradeOrder> to = tradeOrderRepository.findById(id);
        to = Optional.ofNullable(updateTradeOrderRecord(to.get()));
        tradeOrderRepository.save(to.get());
        return to;
    }

    public TradeOrder addTradeOrder(TradeOrder tradeOrder) {
        return tradeOrderRepository.save(updateTradeOrderRecord(tradeOrder));
    }


    public TradeOrder updateTradeOrderRecord (TradeOrder t) {
        double currentPrice=0.0;
        double currentValue=0.0;
        double percentChange=0.0;
        try {
            DecimalFormat df = new DecimalFormat("#.##");

            if (t.getSide().equals("BUY")) {
                SecurityDetail sd = new SecurityDetail();
                sd = utilityService.getSymbolInfo(t.getSecurity());
                if (t.getId() != null) {
                    currentPrice = Double.parseDouble(sd.getLatestPrice());
                    currentValue = currentPrice * t.getQuantity();
                    percentChange = calcPercentChange(t.getPurchaseValue(), currentValue);

                    percentChange = Double.valueOf(df.format(percentChange));

                    t.setCurrentPrice(currentPrice);
                    t.setCurrentValue(currentValue);
                    t.setPercentChange(percentChange);
                } else { //This is a new TradeOrder so all the price fields need to be set
                    t.setCurrentPrice(Double.parseDouble(sd.getLatestPrice()));
                    t.setCurrentValue(t.getQuantity() * t.getCurrentPrice());
                    t.setPurchasePrice(t.getCurrentPrice());
                    t.setPurchaseValue(t.getCurrentValue());
                    t.setPercentChange(new Double(0));
                }
                t.setDateModified(LocalDateTime.now());
            }
        } catch (Exception e) {
            System.out.println("Something went wrong:" + e.toString());
        }
        return t;
    }

    public Iterable<TradeOrder> getTradeOrders(String employeeId) {
        return tradeOrderRepository.findByEmployeeId(employeeId);
    }

    public Iterable<TradeOrder> getAllTradeOrders() {
        return tradeOrderRepository.findAll();
    }



    public List<TradeOrder> getSecurity(String employeeId, String security) {
        return tradeOrderRepository.findByEmployeeIdAndSecurity(employeeId,security);
    }

    public List<TradeOrder> getFund(String employeeId, String fund) {
        return tradeOrderRepository.findByEmployeeIdAndFund(employeeId, fund);
    }


    private Double calcPercentChange(double purchaseValue, double currentValue) {
    //TODO: JUNIT
        double newValue  = 100-((currentValue / purchaseValue) * 100);
        newValue = newValue *-1;
        return newValue;
    }









}
