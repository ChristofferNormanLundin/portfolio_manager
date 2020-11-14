package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.database.TransactionRepository;
import com.stock.portfoliomanager.entity.TransactionEntity;
import com.stock.portfoliomanager.types.Transaction;
import com.stock.portfoliomanager.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TransactionApi {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ObjectConverter objectConverter;

    public List<TransactionEntity> save(Transaction transaction) {
        List<TransactionEntity> entities = objectConverter.getTransactionEntities(transaction);
        return entities.stream()
                .map(transactionEntity -> transactionRepository.save(transactionEntity))
                .collect(Collectors.toList());
    }


    public List<TransactionEntity> findTransactionsByPortfolioId(int portfolioId, int year) {
        return transactionRepository.findByPortfolioId(portfolioId).stream()
                .filter(transactionEntity -> transactionEntity.getTransactionDate().getYear() == year)
                .collect(Collectors.toList());
    }
    public List<TransactionEntity> findTransactionsByPortfolioIdAndStockName(int portfolioId, String stockName) {
        return transactionRepository.findByPortfolioIdAndStockName(portfolioId, stockName);
    }
}
