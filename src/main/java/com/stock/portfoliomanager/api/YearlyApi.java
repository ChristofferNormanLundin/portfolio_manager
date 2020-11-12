package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YearlyApi {

    @Autowired
    PortfolioApi portfolioApi;

    @Autowired
    TransactionApi transactionApi;

    public void getYearlyStatisticsForPortfolio(int portfolioId, int year) {
        PortfolioEntity portfolioEntity = portfolioApi.getPortfolio(portfolioId);
        List<TransactionEntity> t = transactionApi.findTransactionsByPortfolioId(portfolioId, year);
    }
}
