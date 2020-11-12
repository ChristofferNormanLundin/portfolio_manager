package com.stock.portfoliomanager.utils;

import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.Portfolio;
import com.stock.portfoliomanager.types.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ObjectConverter {


    public PortfolioEntity getPortfolioEntity(Portfolio portfolio) {
        PortfolioEntity portfolioEntity = new PortfolioEntity();
        BeanUtils.copyProperties(portfolio, portfolioEntity);
        return portfolioEntity;
    }


    public List<TransactionEntity> getTransactionEntities(Transaction transactions) {
        return transactions.getStocks().stream()
                .map(stock ->
                        TransactionEntity.builder()
                                .portfolioId(transactions.getPortfolioId())
                                .quantity(stock.getQuantity())
                                .sellPrice(stock.getSellPrice())
                                .stockName(stock.getStockName())
                                .payedAmount(stock.getPayedAmount())
                                .transactionDate(transactions.getTransactionDate())
                                .transactionType(transactions.getTransactionType())
                                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                                .build()).collect(Collectors.toList());
    }
}
