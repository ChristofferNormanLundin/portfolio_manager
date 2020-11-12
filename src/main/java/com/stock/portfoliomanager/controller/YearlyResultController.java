package com.stock.portfoliomanager.controller;

import com.stock.portfoliomanager.api.YearlyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/year")
public class YearlyResultController {

    @Autowired
    YearlyApi yearlyApi;

    @RequestMapping(value = "/{year}/{portfolioId}/statistic", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable int portfolioId, @PathVariable int year) {
        yearlyApi.getYearlyStatisticsForPortfolio(portfolioId, year);
        return ResponseEntity.ok().body(null);
    }
}
