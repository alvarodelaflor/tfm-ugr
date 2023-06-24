package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.movement.DisableSensorsMovementAlert;
import com.alvarodelaflor.domain.model.alerts.movement.RepeatedMovementAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.AwakeningsAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.BradycardiaAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

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
        Paragraph sectionTitle2 = new Paragraph("Sintomatología\n", sectionTitle1Font);
        sectionTitle2.setAlignment(Paragraph.ALIGN_LEFT);
        sectionTitle2.setSpacingBefore(8.f);
        document.add(sectionTitle2);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);
        Paragraph paragraph2 = new Paragraph("A continuación, se presentarán síntomas que podrían indicar la existencia de posibles problemas cognitivos. A cada síntoma detectado se asigna un peso que indica la probabilidad de sea cierto." , fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph2);

        workbook = Workbook.builder()
                .commonAlerts(
                        Arrays.asList(
                                DisableSensorsMovementAlert
                                        .builder()
                                        .dateTimeList(Map.of("Lo que sea", Arrays.asList(LocalDateTime.now(), LocalDateTime.now())))
                                        .numberOfTimes(2)
                                        .build(),
                                RepeatedMovementAlert
                                        .builder()
                                        .repeatedMovementNumber(2)
                                        .repeatedMovement(Map.of("Salón", Arrays.asList(LocalDateTime.now(), LocalDateTime.now())))
                                        .build(),
                                AwakeningsAlert
                                        .builder()
                                        .interruptionPeriods(Arrays.asList(
                                                SamsungWearSignal.SleepInterruption
                                                        .builder()
                                                        .start(LocalDateTime.now())
                                                        .end(LocalDateTime.now().plusMinutes(40l))
                                                        .build(),
                                                SamsungWearSignal.SleepInterruption
                                                        .builder()
                                                        .start(LocalDateTime.now().plusMinutes(60l))
                                                        .end(LocalDateTime.now().plusMinutes(100l))
                                                        .build()
                                        ))
                                        .duration(80l)
                                        .numberOfAwakeningInterruptions(2l)
                                        .build(),
                                BradycardiaAlert
                                        .builder()
                                        .avgPulse(56.)
                                    .build()
                        ))
                .build();

        for (CommonAlert commonAlert: workbook.getCommonAlerts()) {
            printAlert(commonAlert, document);
        }

        document.close();
    }

    private void printAlert(CommonAlert commonAlert, Document document) throws DocumentException {
        String descriptionName = commonAlert.getDescriptionName();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(12);
        fontTitle.setStyle(Font.UNDERLINE);
        Paragraph title = new Paragraph(descriptionName, fontTitle);
        title.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        title.setSpacingBefore(8.f);
        title.setSpacingAfter(5.f);

        Anchor anchor = new Anchor("link", FontFactory.getFont(FontFactory.COURIER));
        anchor.setReference(commonAlert.getLink());
        Font linkFont = anchor.getFont();
        linkFont.setColor(Color.BLUE);
        anchor.setFont(linkFont);
        Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph1.setSize(12);
        String text = "Se han detectado evidencias relacionados con " + descriptionName.toLowerCase() + ", aplicando la casuístima disponible en el estudio que puede acceder a través de este ";
        Paragraph paragraph1 = new Paragraph(text , fontParagraph1);
        paragraph1.add(anchor);
        paragraph1.add(new Chunk(". En concreto, se cumple la condición específica en la que se indica que " + StringUtils.uncapitalize(commonAlert.getSummary()) + ". En este caso, esta sintomatología tiene un peso estadístico de " + commonAlert.getWeight().toString().replace(".", ",") + ". " + commonAlert.getCustomText(), fontParagraph1));
        paragraph1.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        document.add(title);
        document.add(paragraph1);
    }

}
