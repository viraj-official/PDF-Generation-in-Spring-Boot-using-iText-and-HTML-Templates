package com.torigan.demo.Controller;
import com.torigan.demo.dto.PdfRequestDto;
import com.torigan.demo.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody PdfRequestDto requestDto) throws IOException {

        // Define the paths to the HTML template and output PDF
        String templatePath = "src/main/resources/templates/invoice_template.html";
        String outputPdfPath = "src/main/resources/output/generated_invoice.pdf";

        // Convert DTO to a Map for placeholder replacement
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("name", requestDto.getName());
        placeholders.put("address", requestDto.getAddress());
        placeholders.put("date", requestDto.getDate());

        // Generate the PDF
        pdfService.generatePdf(templatePath, outputPdfPath, placeholders);

        // Return the PDF as a response
        InputStreamResource resource = new InputStreamResource(new FileInputStream(outputPdfPath));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
