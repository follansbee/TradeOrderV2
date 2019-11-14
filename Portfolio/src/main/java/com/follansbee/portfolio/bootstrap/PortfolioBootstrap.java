package com.follansbee.portfolio.bootstrap;

import com.follansbee.portfolio.models.SecurityDetail;
import com.follansbee.portfolio.models.TradeOrder;
import com.follansbee.portfolio.models.TradeOrderData;
import com.follansbee.portfolio.repository.TradeOrderRepository;
import com.follansbee.portfolio.services.TradeOrderService;
import com.follansbee.portfolio.services.TradeOrderSummaryService;
import com.follansbee.portfolio.services.UtilityService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PortfolioBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    TradeOrderRepository tradeOrderRepository;
    UtilityService utilityService;
    TradeOrderSummaryService tradeOrderSummaryService;

    public PortfolioBootstrap(TradeOrderRepository tradeOrderRepository, TradeOrderSummaryService tradeOrderSummaryService,  UtilityService utilityService) {

        this.tradeOrderRepository = tradeOrderRepository;
        this.utilityService = utilityService;
        this.tradeOrderSummaryService = tradeOrderSummaryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Adding Trade Orders");
        tradeOrderRepository.saveAll(getTradeOrders());

        System.out.println("Updating All Summaries");
        tradeOrderSummaryService.updateAllSummaries();

    }

    public List<TradeOrder> getTradeOrders() {

        List<TradeOrder> tradeOrderList = new ArrayList<>();
        TradeOrder to1 = new TradeOrder(new Long(1),"BUY", "AAPL", "MAG", new Long(10000), LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to2 = new TradeOrder(new Long(2),"BUY", "GOOG", "CONT", new Long(1000), LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to3 = new TradeOrder(new Long(3),"BUY", "RTN", "FP1", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to4 = new TradeOrder(new Long(4),"BUY", "AAPL", "MAG", new Long(2000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to5 = new TradeOrder(new Long(5),"SELL", "T", "F2", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to6 = new TradeOrder(new Long(6),"BUY", "VZ", "CANA", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to7 = new TradeOrder(new Long(7),"BUY", "GOOG", "CANA", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to8 = new TradeOrder(new Long(8),"SELL", "RTN", "FBSC", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to9 = new TradeOrder(new Long(9),"BUY", "AAPL", "FBIO", new Long(2000), LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));
        TradeOrder to10 = new TradeOrder(new Long(10),"SELL", "T", "F2", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"1020552", new Long(66666));

        tradeOrderList.add(to1);
        tradeOrderList.add(to2);
        tradeOrderList.add(to3);
        tradeOrderList.add(to4);
        tradeOrderList.add(to5);
        tradeOrderList.add(to6);
        tradeOrderList.add(to7);
        tradeOrderList.add(to8);
        tradeOrderList.add(to9);
        tradeOrderList.add(to10);

        TradeOrder to11 = new TradeOrder(new Long(11),"BUY", "BH", "MAG", new Long(5000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to12 = new TradeOrder(new Long(12),"BUY", "BH", "CONT", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to13 = new TradeOrder(new Long(13),"BUY", "RTN", "FP1", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to14 = new TradeOrder(new Long(14),"BUY", "BG", "MAG", new Long(2000), LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to15 = new TradeOrder(new Long(15),"BUY", "NE", "F2", new Long(100),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to16 = new TradeOrder(new Long(16),"BUY", "PAM", "CANA", new Long(3000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to17 = new TradeOrder(new Long(17),"BUY", "GOOG", "CANA", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to18 = new TradeOrder(new Long(18),"BUY", "PII", "FBSC", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to19 = new TradeOrder(new Long(19),"BUY", "MC", "FBIO", new Long(600),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));
        TradeOrder to20 = new TradeOrder(new Long(20),"BUY", "SAM", "F2", new Long(1000),  LocalDateTime.now(), LocalDateTime.now(),"18667", new Long(66666));

        tradeOrderList.add(to11);
        tradeOrderList.add(to12);
        tradeOrderList.add(to13);
        tradeOrderList.add(to14);
        tradeOrderList.add(to15);
        tradeOrderList.add(to16);
        tradeOrderList.add(to17);
        tradeOrderList.add(to18);
        tradeOrderList.add(to19);
        tradeOrderList.add(to20);
            tradeOrderList.forEach(tradeOrder -> updateSecurityInfo(tradeOrder));


        return tradeOrderList;
    }

    private TradeOrder updateSecurityInfo (TradeOrder t) {

        SecurityDetail sd = utilityService.getSymbolInfo(t.getSecurity());
        Double currentPrice = Double.valueOf(sd.getLatestPrice());
        t.setPurchasePrice(currentPrice);
        t.setCurrentValue(currentPrice*t.getQuantity());
        t.setPurchaseValue(t.getCurrentValue());
        t.setCurrentPrice(currentPrice);
        t.setPercentChange(Double.valueOf(0));
        return t;



    }
}
