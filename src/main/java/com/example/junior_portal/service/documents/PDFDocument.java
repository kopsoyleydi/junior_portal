package com.example.junior_portal.service.documents;

import com.example.junior_portal.model.Profile;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

@Component
public class PDFDocument {


    public byte[] createPDFForCV(Profile profile) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            Color blackColor = new DeviceRgb(0, 0, 0);

            Paragraph paragraph = new Paragraph("Hello World").setFont(font).setFontSize(16).setFontColor(blackColor);
            Paragraph paragraph2 = new Paragraph("This is another paragraph.").setFont(font).setFontSize(12).setFontColor(blackColor);
            document.add(paragraph);
            document.add(paragraph2);
            document.close();

            byte[] pdfBytes = out.toByteArray();
            FileOutputStream fos = new FileOutputStream("iTextHelloWorld.pdf");
            fos.write(pdfBytes);
            fos.close();

            return pdfBytes;
        } catch (Exception e) {
            // Обработка ошибок
            e.printStackTrace();
            return null;
        }
    }
}
