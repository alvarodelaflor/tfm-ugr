package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, Workbook workbook) throws DocumentException, IOException {
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
        image.setAbsolutePosition(x - 16, document.getPageSize().getHeight() - image.getScaledHeight() - 16);

        // Agregar la imagen al documento
        document.add(image);

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph tittle = new Paragraph("INFORME PRELIMINAR\n", fontTitle);
        tittle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(tittle);

        Font sectionTitle1Font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        sectionTitle1Font.setSize(14);
        sectionTitle1Font.setStyle(Font.ITALIC);
        Paragraph sectionTitle1 = new Paragraph("\nConsideraciones previas\n", sectionTitle1Font);
        sectionTitle1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(sectionTitle1);

        Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph1.setSize(12);
        Paragraph paragraph1 = new Paragraph("Los datos proporcionados en este informe son preliminares y requieren ser estudiados por un profesional de la salud. Están basados en acciones observables y deben ser sometidos a un estudio médico para verificarlos. El sistema se encuentra en fase de pruebas.\n" + "Se recomienda consultar a un médico para obtener una evaluación precisa y completa." , fontParagraph1);
        paragraph1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph1);

        Font sectionTitle2Font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        sectionTitle2Font.setSize(14);
        sectionTitle2Font.setStyle(Font.ITALIC);
        Paragraph sectionTitle2 = new Paragraph("\nSintomatología\n", sectionTitle1Font);
        sectionTitle2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(sectionTitle2);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);
        Paragraph paragraph2 = new Paragraph("A continuación, se presentarán síntomas que podrían indicar la existencia de posibles problemas cognitivos y la probabilidad de la existencia de tales problemas. Cada síntoma detectado se asigna un peso que indica una mayor o menor probabilidad de que dicho problema sea cierto." , fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        paragraph2.setSpacingAfter(10.f);
        document.add(paragraph2);

        workbook = Workbook.builder()
                .commonAlerts(Arrays.asList(CommonAlert.builder().build()))
                .build();

        for (CommonAlert commonAlert: workbook.getCommonAlerts()) {
            printAlert(commonAlert, document);
        }

        document.close();
    }

    private void printAlert(CommonAlert commonAlert, Document document) throws DocumentException {
        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);
        Paragraph paragraph2 = new Paragraph("A continuación, se presentarán síntomas que podrían indicar la existencia de posibles problemas cognitivos y la probabilidad de la existencia de tales problemas. Cada síntoma detectado se asigna un peso que indica una mayor o menor probabilidad de que dicho problema sea cierto." , fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        paragraph2.setSpacingAfter(10.f);
        document.add(paragraph2);
    }
}
