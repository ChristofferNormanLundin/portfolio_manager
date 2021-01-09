package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.exceptions.InternalException;
import com.stock.portfoliomanager.types.SpecificStock;
import com.stock.portfoliomanager.types.TransactionTypes;
import com.stock.portfoliomanager.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockApi {

    @Autowired
    TransactionApi transactionApi;

    private double PAYED_AMOUNT;
    private double SELL_AMOUNT;
    private int STOCK_AMOUNT;
    private int SOLD_STOCK_AMOUNT;
    private int STOCKS_LEFT;
    private double EARNINGS;
    private double AVERAGE_ACQUISTION_VALUE;

    @SneakyThrows
    public SpecificStock getSpecificStock(int portfolioId, String stockName) {
        List<TransactionEntity> transactionEntities = transactionApi.findTransactionsByPortfolioIdAndStockName(portfolioId, stockName);
        calculateAmounts(transactionEntities);

        return getSpecificStockObject(stockName);
    }

    @SneakyThrows
    public SpecificStock getSpecifickStock(List<TransactionEntity> transactionEntities) {
        calculateAmounts(transactionEntities);
        return getSpecificStockObject(transactionEntities.get(0).getStockName());
    }

    private SpecificStock getSpecificStockObject(String stockName) {
        return SpecificStock.builder()
                .name(stockName)
                .averageAcquisitionValue(AVERAGE_ACQUISTION_VALUE)
                .payed(PAYED_AMOUNT)
                .sold(SELL_AMOUNT)
                .earnings(EARNINGS)
                .stocksLeft(STOCKS_LEFT)
                .build();
    }

    private void calculateAmounts(List<TransactionEntity> transactionEntities) throws InternalException {
        setValues();

        transactionEntities.stream()
                .filter(transactionEntity -> transactionEntity.getTransactionType() == TransactionTypes.BUY)
                .collect(Collectors.toList()).forEach(transactionEntity -> {
            PAYED_AMOUNT += transactionEntity.getAmount();
            STOCK_AMOUNT += transactionEntity.getQuantity();
        });

        AVERAGE_ACQUISTION_VALUE = Utils.roundToTwoDecimals((PAYED_AMOUNT / STOCK_AMOUNT));

        transactionEntities.stream()
                .filter(transactionEntity -> transactionEntity.getTransactionType() == TransactionTypes.SELL)
                .collect(Collectors.toList())
                .forEach(transactionEntity -> {
                    SELL_AMOUNT += transactionEntity.getAmount();
                    SOLD_STOCK_AMOUNT += transactionEntity.getQuantity();
                });

        EARNINGS = Utils.roundToTwoDecimals(SELL_AMOUNT > 0 ? (SELL_AMOUNT - PAYED_AMOUNT) : 0);
        STOCKS_LEFT = STOCK_AMOUNT - SOLD_STOCK_AMOUNT;
        if (STOCKS_LEFT < 0) {
            throw new InternalException("Stocks left is: " + STOCKS_LEFT + " stock: " + transactionEntities.get(0).getStockName());
        }
    }

    private void setValues() {
        PAYED_AMOUNT = 0;
        SELL_AMOUNT = 0;
        STOCK_AMOUNT = 0;
        SOLD_STOCK_AMOUNT = 0;
        STOCKS_LEFT = 0;
        EARNINGS = 0;
        AVERAGE_ACQUISTION_VALUE = 0;
    }
}
