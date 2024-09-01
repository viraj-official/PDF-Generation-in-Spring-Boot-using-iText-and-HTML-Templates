package com.torigan.demo.service;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class PDFService
{
    public void generatePdf(String templatePath, String outputPdfPath, Map<String, String> placeholders) throws IOException
    {
        // Read the HTML template as a string
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);

        // Replace placeholders with actual values
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        // Convert the updated HTML content to PDF
        HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath));
    }
}