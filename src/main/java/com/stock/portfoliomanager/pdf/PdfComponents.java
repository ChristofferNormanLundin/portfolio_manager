package com.stock.portfoliomanager.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import org.springframework.stereotype.Component;

@Component
public class PdfComponents {

    public Paragraph getParagraph(String text, int alignment, int size, int fontStyle) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setAlignment(alignment);
        paragraph.setFont(getFont(size, fontStyle));
        return paragraph;
    }

    private Font getFont(int size, int fontStyle) {
        return new Font(Font.FontFamily.TIMES_ROMAN, size, fontStyle);
    }

    public PdfPCell simpleTextField(String text, Font font) {
        PdfPCell cell = new PdfPCell();
        return cell;
    }
}
