package com.stock.portfoliomanager.api;

import com.stock.portfoliomanager.database.PortfolioRepository;
import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.types.Portfolio;
import com.stock.portfoliomanager.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortfolioApi {

    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ObjectConverter objectConverter;

    public PortfolioEntity savePortfolio(Portfolio portfolio) {
        PortfolioEntity entity = objectConverter.getPortfolioEntity(portfolio);
        portfolioRepository.save(entity);
        return entity;
    }

    public PortfolioEntity getPortfolio(int portfolioId) {
        return portfolioRepository.findById(portfolioId).orElse(null);
    }
}
