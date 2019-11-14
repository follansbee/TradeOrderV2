package com.follansbee.portfolio.controllers;

import com.follansbee.portfolio.models.*;
import com.follansbee.portfolio.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfolio/")
@CrossOrigin(origins = "*")
public class PortfolioController {


    public PortfolioController(UserService userService, GroupService groupService, TradeOrderService tradeOrderService, MessageService messageService, TradeOrderSummaryService tradeOrderSummaryService, UtilityService utilityService, TradeOrderHistoryService historyService) {
        this.userService = userService;
        this.groupService = groupService;
        this.tradeOrderService = tradeOrderService;
        this.messageService = messageService;
        this.tradeOrderSummaryService = tradeOrderSummaryService;
        this.utilityService = utilityService;
        this.historyService = historyService;
    }

    private UserService userService;
    private GroupService groupService;
    private TradeOrderService tradeOrderService;
    private UtilityService utilityService;
    private TradeOrderSummaryService tradeOrderSummaryService;
    private MessageService messageService;
    private TradeOrderHistoryService historyService;

    @RequestMapping(value = "user/", method = RequestMethod.GET)
    public List<Users> getUsers() { //this updates the symbol repository
        return userService.getUsers();
    }

    @RequestMapping(value = "user/{employeeid}", method = RequestMethod.GET)
    public Users getUser(@PathVariable String employeeid) { //this gets an individual User
        Optional<Users> u = userService.getUser(employeeid);
        return u.get();
    }

    @RequestMapping(value = "user/{employeeid}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String employeeid) { //this gets an individual User
        userService.delete(employeeid);
        return "Delete of " + employeeid;
    }

    @RequestMapping(value = "user/", method = RequestMethod.POST)
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        userService.addUser(user);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    ////////////////////////////
    // GROUPS
    ////////////////////////////

    @RequestMapping(value="group/", method = RequestMethod.GET)
    public Iterable<UserGroup> getGroups() {
        return groupService.getGroups();
    }

    @RequestMapping(value = "group/", method = RequestMethod.POST)
    public ResponseEntity<UserGroup> addGroup(@RequestBody UserGroup group) {
        groupService.addGroup(group);
        return new ResponseEntity<UserGroup>(group, HttpStatus.OK);
    }

    @RequestMapping(value="group/{groupId}/user/{employeeId}", method = RequestMethod.GET)
    public Iterable<UserGroup> assignUserToGroup(@PathVariable long groupId, @PathVariable String employeeId) {
        System.out.println("In Test Mapping");
        userService.addUserToGroup(groupId, employeeId);
        return groupService.getGroups();
    }

    @RequestMapping(value = "group/{groupId}", method = RequestMethod.GET)
    public UserGroup getGroup(@PathVariable Long groupId) { //this gets an individual User
        Optional<UserGroup> u = groupService.getGroup(groupId);
        return u.get();
    }

    ///TRADER ORDERS

    @RequestMapping(value = "tradeorder/", method = RequestMethod.GET)
    public List<TradeOrder> getTradeOrders() { //this updates the symbol repository

        return (List<TradeOrder>) tradeOrderService.getAllTradeOrders();
    }

    @RequestMapping(value = "tradeorder/user/{employeeId}", method = RequestMethod.GET)
    public List<TradeOrder> getTradeOrdersForUser(@PathVariable String employeeId) { //this updates the symbol repository

        return (List<TradeOrder>) tradeOrderService.getTradeOrders(employeeId);
    }


    @RequestMapping(value ="tradeorder/{tradeOrderId}", method = RequestMethod.GET)
    public Optional<TradeOrder> getTradeOrder(@PathVariable Long tradeOrderId) {
        Optional<TradeOrder> to = tradeOrderService.getTradeOrder(tradeOrderId);
        return to;
    }

    @RequestMapping(value = "tradeorder/", method = RequestMethod.POST)
    public ResponseEntity<TradeOrder> addTradeOrder(@RequestBody TradeOrder tradeOrder) {
        tradeOrderService.addTradeOrder(tradeOrder);
        messageService.sendMessages("New Trade Order Added");
        return new ResponseEntity<TradeOrder>(tradeOrder, HttpStatus.OK);
    }


    @RequestMapping(value = "reports/summary/user/{employeeid}", method = RequestMethod.GET)
    public Optional<Summary> getUserSummaryData(@PathVariable String employeeid) { //this gets an individual User
//        return portfolioSummary.getPortfolioSummaryById(employeeid);
        System.out.println("New Summary");
        return tradeOrderService.getTradeOrderSummaryById(employeeid);
    }

    @RequestMapping(value = "reports/summary/user/", method = RequestMethod.GET)
    public List<Summary> getAllSummaryData() { //this gets an individual User

        //return calculationService.getPortfolioTotal((ArrayList<TradeOrder>) tradeOrderService.getTradeOrders(employeeId), employeeId);
        //return portfolioSummary.getAllPortfolioSummary();
        return tradeOrderService.getAllTradeOrderSummary();
    }
    //Security
    @RequestMapping(value = "reports/summary/security/user/{employeeid}", method = RequestMethod.GET )
    public List<SecuritySummary> getSecuritySummary(@PathVariable String employeeid)  {
        return tradeOrderService.getSecuritySummary(employeeid);
    }

    @RequestMapping(value = "reports/summary/security/{symbol}/user/{employeeid}", method = RequestMethod.GET )
    public Optional<SecuritySummary> getSecuritySummary(@PathVariable String symbol, @PathVariable String employeeid)  {
        return tradeOrderService.getSecuritySummary(symbol, employeeid);
    }

    //Fund
    @RequestMapping(value = "reports/summary/fund/user/{employeeid}", method = RequestMethod.GET )
    public List<FundSummary> getFundSummary(@PathVariable String employeeid)  {
        return tradeOrderService.getFundSummary(employeeid);
    }

    @RequestMapping(value = "reports/summary/fund/{fund}/user/{employeeid}", method = RequestMethod.GET )
    public Optional<FundSummary> getFundSummary(@PathVariable String fund, @PathVariable String employeeid)  {
        return tradeOrderService.getFundSummary(fund, employeeid);
    }


    @RequestMapping(value = "reports/summary/user/update/", method = RequestMethod.GET)
    public String forceUpdate() { //Updates the summary data for everyone

        //This will not work if a
        messageService.sendMessages("SummaryData");//Send Message to update the summary Data

        return "Update Summary Data Requested";
    }

    //Stock status
    @RequestMapping(value = "stock/{symbol}", method = RequestMethod.GET)
    public SecurityDetail getStockData(@PathVariable String symbol) { //this gets an individual User

        //THIS WILL BREAK IF THE AMAZON SERVICE IS NOT RUNNING
        return utilityService.getSymbolInfo(symbol);

    }

    @RequestMapping(value = "stock/", method = RequestMethod.GET)
    public List<Security> getStocks() throws MessagingException { //this gets an individual User
        //calculationService.updateUserStats();
        //THIS WILL BREAK IF THE AMAZON SERVICE IS NOT RUNNING
        return utilityService.getSymbolInfo();

    }
    @RequestMapping(value = "test/", method = RequestMethod.GET)
    public String getTest() throws MessagingException { //this gets an individual User

        //tradeOrderService.testDistinct();

        //tradeOrderSummaryService.updateAllTradeOrders((ArrayList<TradeOrder>) tradeOrderService.getTradeOrders("1020552"));
        //tradeOrderSummaryService.updateAllTradeOrders((ArrayList<TradeOrder>) tradeOrderService.getTradeOrders("18667"));
        historyService.updateDailySummary();
        return "A full update was made and the history table should be populated";

    }
    //History
    @RequestMapping(value="history/", method = RequestMethod.GET)
    public List<PortfolioHistory> getHistory() {
        return (List<PortfolioHistory>) historyService.getHistory();
    }

    @RequestMapping(value="history/user/{employeeid}", method = RequestMethod.GET)
    public List<PortfolioHistory> getHistoryByUser(@PathVariable String employeeid) {
        return (List<PortfolioHistory>) historyService.getHistory(employeeid);
    }

}
