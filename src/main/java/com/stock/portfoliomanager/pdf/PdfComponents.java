package com.stock.portfoliomanager.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock.portfoliomanager.types.YearlyStatistics;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfComponents {

    private final int fontSize = 10;
    private final int BOLD = Font.BOLD;
    private final int NORMAL = Font.NORMAL;

    @SneakyThrows
    public PdfPTable createTable(int[] columnWidths) {
        PdfPTable table = new PdfPTable(columnWidths.length);
        table.setWidths(columnWidths);
        return table;
    }


    public PdfPTable createColumns(PdfPTable table, List<String> columns, int size, int fontStyle, BaseColor color) {
        columns.forEach(columnName -> table.addCell(createCell(columnName, size, fontStyle, color)));
        return table;
    }


    public PdfPTable createRows(PdfPTable table, YearlyStatistics statistics) {
        statistics.getSpecificStocks()
                .forEach(stock ->
                {
                    table.addCell(createCell(stock.getName(), fontSize, NORMAL, BaseColor.WHITE));
                    table.addCell(createCell(String.valueOf(stock.getStocksLeft()), fontSize, NORMAL, BaseColor.WHITE));
                    table.addCell(createCell(String.valueOf(stock.getAverageAcquisitionValue()), fontSize, NORMAL, BaseColor.WHITE));
                    table.addCell(createCell(String.valueOf(stock.getPayed()), fontSize, NORMAL, BaseColor.WHITE));
                    table.addCell(createCell(String.valueOf(stock.getSold()), fontSize, NORMAL, BaseColor.WHITE));
                    table.addCell(createCell(String.valueOf(stock.getEarnings()), fontSize, NORMAL, BaseColor.WHITE));
                });

        return table;
    }

    public Paragraph createParagraph(String text, int alignment, int size, int fontStyle, float spaceAfter) {
        Paragraph paragraph = new Paragraph(text, getFont(size, fontStyle));
        paragraph.setAlignment(alignment);
        paragraph.setSpacingAfter(spaceAfter);
        return paragraph;
    }


    private PdfPCell createCell(String text, int size, int fontStyle, BaseColor color) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(color);
        cell.setBorderWidth(1);
        cell.setBorderColor(BaseColor.DARK_GRAY);
        cell.setPhrase(createPhrase(text, size, fontStyle));
        cell.setNoWrap(false);
        return cell;
    }

    private Phrase createPhrase(String text, int size, int fontStyle) {
        return new Phrase(text, getFont(size, fontStyle));
    }

    private Font getFont(int size, int fontStyle) {
        return new Font(Font.FontFamily.TIMES_ROMAN, size, fontStyle);
    }
}
