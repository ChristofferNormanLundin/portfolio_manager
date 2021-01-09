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
        List<TransactionEntity> transactions = transactionApi.findTransactionsByPortfolioId(portfolioId);
        List<SpecificStock> specificStocks = calulateBoughtAndSold(transactions, year);

        return YearlyStatistics.builder()
                .portfolio(portfolio)
                .specificStocks(specificStocks)
                .invested(totalInvested(specificStocks))
                .build();
    }

    private double totalInvested(List<SpecificStock> specificStocks) {
        double invested = 0;
        for (SpecificStock s : specificStocks) {
            invested += s.getPayed();
        }
        return invested;
    }

    private List<SpecificStock> calulateBoughtAndSold(List<TransactionEntity> transactions, int year) {
        List<SpecificStock> specificStocks = new ArrayList<>();
        List<String> memory = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            if (!memory.contains(transaction.getStockName())) {
                memory.add(transaction.getStockName());

                //Filter all transactions on the stock name
                //that is before and up to this year.
                List<TransactionEntity> stocks = transactions.stream()
                        .filter(stock -> stock.getStockName().equals(transaction.getStockName())
                                && stock.getTransactionDate().getYear() <= year)
                        .collect(Collectors.toList());

                //Check that atleast one of the trades is from requested year
                boolean result = stocks.stream()
                        .filter(stock -> stock.getTransactionDate().getYear() == year)
                        .collect(Collectors.toList()).size() > 1;

                if (result) {
                    specificStocks.add(stockApi.getSpecifickStock(stocks));
                }
            }
        }
        return specificStocks;
    }
}
