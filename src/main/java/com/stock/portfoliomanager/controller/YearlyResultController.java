package com.stock.portfoliomanager.controller;

import com.stock.portfoliomanager.api.YearlyApi;
import com.stock.portfoliomanager.pdf.PdfBuilder;
import com.stock.portfoliomanager.types.YearlyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/year")
public class YearlyResultController {

    @Autowired
    YearlyApi yearlyApi;

    @Autowired
    PdfBuilder pdfBuilder;

    @RequestMapping(value = "/{year}/{portfolioId}/statistic", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable int portfolioId, @PathVariable int year) {
        YearlyStatistics yearlyStatistics = yearlyApi.getYearlyStatisticsForPortfolio(portfolioId, year);
        return ResponseEntity.ok().body(yearlyStatistics);
    }

    @RequestMapping(value = "/{year}/{portfolioId}/pdf", method = RequestMethod.GET)
    public ResponseEntity getPdf(@PathVariable int portfolioId, @PathVariable int year) {
        YearlyStatistics yearlyStatistics = yearlyApi.getYearlyStatisticsForPortfolio(portfolioId, year);
        pdfBuilder.createYearlyStatisticsPdf(yearlyStatistics);
        return ResponseEntity.ok().body(null);
    }

}
