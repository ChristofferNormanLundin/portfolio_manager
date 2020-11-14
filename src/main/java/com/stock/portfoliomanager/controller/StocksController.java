package com.stock.portfoliomanager.controller;

import com.stock.portfoliomanager.api.StockApi;
import com.stock.portfoliomanager.types.SpecificStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
@Slf4j
public class StocksController {

    @Autowired
    StockApi stockApi;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<SpecificStock> getPortfolio(@RequestParam int portfolioId, @RequestParam String stockName) {
        return ResponseEntity.ok(stockApi.getSpecificStock(portfolioId, stockName));
    }
}
