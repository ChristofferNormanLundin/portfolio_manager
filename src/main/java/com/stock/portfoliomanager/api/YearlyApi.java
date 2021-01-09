package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.SpecificStock;
import com.stock.portfoliomanager.types.YearlyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class YearlyApi {

    @Autowired
    PortfolioApi portfolioApi;

    @Autowired
    TransactionApi transactionApi;

    @Autowired
    StockApi stockApi;

    public YearlyStatistics getYearlyStatisticsForPortfolio(int portfolioId, int year) {
        PortfolioEntity portfolio = portfolioApi.getPortfolio(portfolioId);
        List<TransactionEntity> transactions = transactionApi.findTransactionsByPortfolioId(portfolioId, year);
        List<SpecificStock> specificStocks = calulateBoughtAndSold(transactions);

        return YearlyStatistics.builder()
                .portfolio(portfolio)
                .specificStocks(specificStocks)
                .build();
    }

    private List<SpecificStock> calulateBoughtAndSold(List<TransactionEntity> transactions) {
        List<SpecificStock> specificStocks = new ArrayList<>();
        List<String> memory = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            if (!memory.contains(transaction.getStockName())) {
                memory.add(transaction.getStockName());

                List<TransactionEntity> stocks = transactions.stream()
                        .filter(stock -> stock.getStockName().equals(transaction.getStockName()))
                        .collect(Collectors.toList());

                specificStocks.add(stockApi.getSpecifickStock(stocks));

            }
        }
        return specificStocks;
    }
}
