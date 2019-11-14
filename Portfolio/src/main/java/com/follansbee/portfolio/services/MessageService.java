package com.follansbee.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Component
public class MessageService {
    @Autowired
    private ApplicationContext appContext;
    private EmailService emailService;
    private TradeOrderSummaryService tradeOrderSummaryService;

    public MessageService(TradeOrderSummaryService tradeOrderSummaryService, EmailService emailService) {
        this.tradeOrderSummaryService = tradeOrderSummaryService;
        this.emailService = emailService;
    }

    public void sendMessages() {
           sendMessages("Default-Message");
    }

    public void sendMessages(String messageDetail) {
        JmsTemplate jmsTemplate = appContext.getBean(JmsTemplate.class);
        LocalDateTime myDate = now();
        jmsTemplate.convertAndSend("tradeOrdersQueue", "New TradeOrders - " + myDate  + " - " + messageDetail);
    }

//    @JmsListener(destination = "tradeOrdersQueueDev")
    @JmsListener(destination="tradeOrdersQueue")
    public void receiveMessages(String message) throws MessagingException {
        System.out.println("message received");
        if(message.contains("New TradeOrders")) {
//            summaryService.updateAllTradeOrders((ArrayList<TradeOrder>) repo.findAll());

            //TODO - pass the employeeID so only the summaries for that employee are updated.
            tradeOrderSummaryService.updateAllSummaries();
            emailService.sendEmail("dave.follansbee@gmail.com", "Received a Message", "Trade Order Queues are Updated " + LocalDateTime.now());

        }
    }


}
