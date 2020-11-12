package com.stock.portfoliomanager.controller;

import com.stock.portfoliomanager.api.PortfolioApi;
import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.database.PortfolioRepository;
import com.stock.portfoliomanager.types.Portfolio;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/portfolio")
@Slf4j
public class PortfolioController {


    @Autowired
    PortfolioApi portfolioApi;

    @RequestMapping(value = "/{portfolioId}", method = RequestMethod.GET)
    public ResponseEntity<PortfolioEntity> getPortfolio(@PathVariable int portfolioId) {
        PortfolioEntity portfolioEntity = portfolioApi.getPortfolio(portfolioId);

        if (portfolioEntity != null) {
            return ResponseEntity.ok(portfolioEntity);
        }
        log.error("Could not get portfolio with id " + portfolioId);
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<PortfolioEntity> createPortfolio(@RequestBody Portfolio portfolio) {
        PortfolioEntity entity = portfolioApi.savePortfolio(portfolio);
        return ResponseEntity.ok(entity);
    }
}



