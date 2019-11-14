package com.follansbee.portfolio.repository;

import com.follansbee.portfolio.models.SecurityDetail;
import com.follansbee.portfolio.models.TradeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeOrderRepository extends CrudRepository<TradeOrder, Long> {
    List<TradeOrder> findByEmployeeId(String side);
    List<TradeOrder> findByEmployeeIdAndSecurity(String employeeId, String security);
    List<TradeOrder>  findByEmployeeIdAndFund(String employeeId, String fund);
    List<TradeOrder> findByEmployeeIdAndSide(String employeeId, String side);
}
