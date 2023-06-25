package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ExecutionService {

    @Autowired
    PDFGeneratorService pdfGeneratorService;
    @Autowired
    ActionService actionService;
    @Autowired
    RedisService redisService;


    public void executeWorkbook(HttpServletResponse response, String username) throws DocumentException, IOException {
        Workbook workbook = redisService.getWorkbookByUsername(username);

        pdfGeneratorService.export(response, workbook);
        actionService.executeAction(workbook);
    }
}
