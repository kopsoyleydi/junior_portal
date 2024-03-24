package com.example.junior_portal.service.documents;

import com.example.junior_portal.model.Profile;
import com.example.junior_portal.service.portal.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class PDFGenerator {

    private final ProfileService studentService;

    public File generatePdf(Profile profile) throws IOException {
        return renderPdf(profile);
    }


    private File renderPdf(Profile profile) throws IOException {
        File file = File.createTempFile("profile", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

}
