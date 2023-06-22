package com.alvarodelaflor.execution.services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("static/logo.png");
        Image image = Image.getInstance(inputStream.readAllBytes());
        image.scalePercent(3.f);

        // Establecer los márgenes del documento
        document.setMargins(0, 0, 0, 0);

        // Establecer el ancho y la posición de la imagen
        float documentWidth = document.getPageSize().getWidth();
        float imageWidth = image.getScaledWidth();
        float x = documentWidth - imageWidth;

        // Posicionar la imagen en la esquina superior derecha
        image.setAbsolutePosition(x - 10, document.getPageSize().getHeight() - image.getScaledHeight() - 10);

        // Agregar la imagen al documento
        document.add(image);

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph tittle = new Paragraph("INFORME PRELIMINAR\n", fontTitle);
        tittle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(tittle);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph paragraph1 = new Paragraph("This is a paragraph" , fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph1);

        document.close();
    }
}
