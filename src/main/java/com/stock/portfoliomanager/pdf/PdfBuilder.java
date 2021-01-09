package com.stock.portfoliomanager.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stock.portfoliomanager.types.YearlyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class PdfBuilder {

    @Autowired
    PdfComponents components;

    private final String SPACE = " ";

    public void createYearlyStatisticsPdf(YearlyStatistics statistics) {
        createDocument(statistics);
    }

    public void createDocument(YearlyStatistics statistics) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

            document.open();
            document.add(addHeader(statistics));
            document.add(addPortfolioTable());
            /*PdfPTable table = new PdfPTable(1);
            addTextCenter(table, statistics);*/



            /*addTableHeader(table);
            addRows(table);*/
            /*addCustomRows(table);*/

            /* document.add(table);*/
            document.close();
        } catch (Exception e) {

        }
    }

    private Paragraph addHeader(YearlyStatistics statistics) {
        String text = "Årsbesked " + statistics.getYear();
        return components.getParagraph(text, Element.ALIGN_CENTER, 12, Font.NORMAL);
    }


    private PdfPTable addPortfolioTable() {
        PdfPTable table = new PdfPTable(6);
        Stream.of("Namn", "Antal", "Gav", "Investerat", "Sålt", "Vinst")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        return table;
    }


    private void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }
}
