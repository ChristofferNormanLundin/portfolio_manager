package com.stock.portfoliomanager.database;


import com.stock.portfoliomanager.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByPortfolioId(int portfolioId);

    List<TransactionEntity> findByPortfolioIdAndStockName(int portfolioId, String stockName);

}
