package com.stock.portfoliomanager.utils;

import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.Portfolio;
import com.stock.portfoliomanager.types.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public final class ObjectConverter {


    public PortfolioEntity getPortfolioEntity(Portfolio portfolio) {
        PortfolioEntity portfolioEntity = new PortfolioEntity();
        BeanUtils.copyProperties(portfolio, portfolioEntity);
        return portfolioEntity;
    }

    public TransactionEntity getTransactionEntity(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        BeanUtils.copyProperties(transaction, transactionEntity);
        transactionEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return transactionEntity;
    }
}
