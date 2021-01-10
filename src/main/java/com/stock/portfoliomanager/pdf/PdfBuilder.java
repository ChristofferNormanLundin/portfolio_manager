package com.stock.portfoliomanager.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stock.portfoliomanager.types.YearlyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.List;

@Component
public class PdfBuilder {

    @Autowired
    PdfComponents components;

    public void createYearlyStatisticsPdf(YearlyStatistics statistics) {
        createDocument(statistics);
    }

    public void createDocument(YearlyStatistics statistics) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Portfolio.pdf"));

            document.open();
            document.add(addHeader(statistics));
            document.add(addPortfolioName(statistics.getPortfolio().getPortfolioName()));
            document.add(addPortfolioTable(statistics));

            document.close();
        } catch (Exception e) {

        }
    }

    private Paragraph addHeader(YearlyStatistics statistics) {
        String text = "Årsbesked " + statistics.getYear();
        Paragraph paragraph = components.createParagraph(text, Element.ALIGN_CENTER, 14, Font.NORMAL, 10L);
        return paragraph;
    }

    //TODO Fix alignment
    private Paragraph addPortfolioName(String portfolioName) {
        String text = "Portfölj: " + portfolioName;
        Paragraph paragraph = components.createParagraph(text, Element.ALIGN_LEFT, 8, Font.NORMAL, 5L);
        return paragraph;
    }

    private PdfPTable addPortfolioTable(YearlyStatistics statistics) {
        List<String> columns = List.of("Aktie", "Antal", "Gav", "Investerat", "Sålt", "Vinst");
        int[] columnWidths = {2, 1, 1, 2, 1, 1};

        PdfPTable table = components.createTable(columnWidths);
        table = components.createColumns(table, columns, 14, Font.BOLD, BaseColor.LIGHT_GRAY);
        table = components.createRows(table, statistics);
        return table;
    }
}
