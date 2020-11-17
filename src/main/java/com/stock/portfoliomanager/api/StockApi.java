package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.SpecificStock;
import com.stock.portfoliomanager.types.TransactionTypes;
import com.stock.portfoliomanager.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockApi {

    @Autowired
    TransactionApi transactionApi;

    @Autowired
    Utils utils;

    private double PAYED_AMOUNT = 0;
    private double SELL_AMOUNT = 0;
    private int STOCK_AMOUNT = 0;
    private int SOLD_STOCK_AMOUNT = 0;
    private int STOCKS_LEFT = 0;
    private double EARNINGS = 0;


    private double AVERAGE_ACQUISTION_VALUE = 0;

    public SpecificStock getSpecificStock(int portfolioId, String stockName) {
        List<TransactionEntity> transactionEntities = transactionApi.findTransactionsByPortfolioIdAndStockName(portfolioId, stockName);
        calculateAmounts(transactionEntities);

        return SpecificStock.builder()
                .name(stockName)
                .averageAcquisitionValue(AVERAGE_ACQUISTION_VALUE)
                .payedAmount(PAYED_AMOUNT)
                .sellAmount(SELL_AMOUNT)
                .earnings(EARNINGS)
                .stocksLeft(STOCKS_LEFT)
                .build();

        //TODO set all params to 0 again!
    }


    private void calculateAmounts(List<TransactionEntity> transactionEntities) {
        transactionEntities.stream()
                .filter(transactionEntity -> transactionEntity.getTransactionType() == TransactionTypes.BUY)
                .collect(Collectors.toList()).forEach(transactionEntity -> {
            PAYED_AMOUNT += transactionEntity.getPayedAmount();
            STOCK_AMOUNT += transactionEntity.getQuantity();
        });

        AVERAGE_ACQUISTION_VALUE = utils.roundToTwoDecimals((PAYED_AMOUNT / STOCK_AMOUNT));

        transactionEntities.stream()
                .filter(transactionEntity -> transactionEntity.getTransactionType() == TransactionTypes.SELL)
                .collect(Collectors.toList())
                .forEach(transactionEntity -> {
                    SELL_AMOUNT += transactionEntity.getSellPrice();
                    SOLD_STOCK_AMOUNT += transactionEntity.getQuantity();
                });

        EARNINGS = (SELL_AMOUNT - PAYED_AMOUNT);
        STOCKS_LEFT = STOCK_AMOUNT - SOLD_STOCK_AMOUNT;
    }
}
