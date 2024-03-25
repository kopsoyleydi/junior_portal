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
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

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


            Paragraph personalInfoContent = new Paragraph("Name: Kuralbai Bexultan\nAddress: Almaty, Kazakhstan ");
            document.add(personalInfoContent);

            Paragraph educationContent = new Paragraph("Information Security\nUniversity: IITU\nGraduated: 2025");
            document.add(educationContent);

            Paragraph experienceContent = new Paragraph("Java Developer\nКомпания: Примерная компания\nГоды работы: 2018-2022");
            document.add(experienceContent);

            List skillsList = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022")
                    .setFont(font)
                    .setFontColor(blackColor);
            skillsList.add(new ListItem("Java"));
            skillsList.add(new ListItem("Python"));
            document.add(skillsList);

            Paragraph additionalInfoContent = new Paragraph("Дополнительная информация: Примерная дополнительная информация");
            document.add(additionalInfoContent);

            document.close();



            byte[] pdfBytes = out.toByteArray();
            FileOutputStream fos = new FileOutputStream("iTextHelloWorld.pdf");
            fos.write(pdfBytes);
            fos.close();

            return pdfBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
