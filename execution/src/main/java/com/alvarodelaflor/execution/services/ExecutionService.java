package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExecutionService {

    @Autowired
    PDFGeneratorService pdfGeneratorService;
    @Autowired
    ActionService actionService;
    @Autowired
    RedisService redisService;


    public void executeWorkbook(HttpServletResponse response, String username) throws DocumentException, IOException {
        List<Workbook> workbookList = redisService.getWorkbookByUsername(username);
        for (Workbook workbook: workbookList) {
            pdfGeneratorService.export(response, workbook);
            actionService.executeAction(workbook);
            redisService.deleteWorkbookByUsernameAndId(username, workbook.getId());
        }
    }
}
