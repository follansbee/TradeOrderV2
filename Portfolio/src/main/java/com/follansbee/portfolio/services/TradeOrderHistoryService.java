package com.follansbee.portfolio.services;

import com.follansbee.portfolio.models.PortfolioHistory;
import com.follansbee.portfolio.models.PortfolioHistoryId;
import com.follansbee.portfolio.models.Summary;
import com.follansbee.portfolio.models.Users;
import com.follansbee.portfolio.repository.PortfolioHistoryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TradeOrderHistoryService {

    UserService userService;
    TradeOrderService tradeOrderService;
    TradeOrderSummaryService summaryService;
    PortfolioHistoryRepository portfolioHistoryRepository;
    EmailService emailService;

    TradeOrderHistoryService (UserService userService, TradeOrderService tradeOrderService, EmailService emailService, TradeOrderSummaryService summaryService, PortfolioHistoryRepository portfolioHistoryRepository) {
        this.userService = userService;
        this.tradeOrderService = tradeOrderService;
        this.summaryService = summaryService;
        this.portfolioHistoryRepository = portfolioHistoryRepository;
        this.emailService = emailService;
    }

    public List<PortfolioHistory> getHistory() {
        return (List<PortfolioHistory>) portfolioHistoryRepository.findAll();
    }

    public List<PortfolioHistory> getHistory(String employeeId) {
        return (List<PortfolioHistory>) portfolioHistoryRepository.findByUserId(employeeId);
    }

    @Scheduled(cron = "0 0 23 * * ?")  //11PM
    // @Scheduled(cron = "0/30 * * * * ?")  //30 seconds
    public void updateDailySummary() throws MessagingException {
        System.out.println("History Update");
        saveDailyHistory();
        emailService.sendEmail("dave.follansbee@gmail.com", "Update Portfolio History", "The Historic Portfolio totals have been updated for " + LocalDateTime.now());

    }

    public void saveDailyHistory() {
        //Updates all the Summaries
        summaryService.updateAllSummaries();

        List<Users> userList = userService.getUsers();
        PortfolioHistoryId phId = new PortfolioHistoryId();
        phId.setDate(LocalDateTime.now().getMonth()+ "_" + LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getYear());
        for (Users user : userList) {
            Optional<Summary> summary = tradeOrderService.getTradeOrderSummaryById(user.getEmployeeId());

            if(summary.isPresent()) {
                phId.setEmployeeId(user.getEmployeeId());
                PortfolioHistory p = new PortfolioHistory(phId, user.getEmployeeId(), summary.get().getTotalNumOfOrders(),summary.get().getTotalNumOfShares(),summary.get().getTotalValue(), summary.get().getAvgPrice());
                portfolioHistoryRepository.save(p);
            }
        }
    }
}
