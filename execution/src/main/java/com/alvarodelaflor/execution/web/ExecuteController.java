package com.alvarodelaflor.execution.web;

import com.alvarodelaflor.execution.services.ExecutionService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/execute")
public class ExecuteController {

    @Autowired
    private ExecutionService executionService;

    @GetMapping("/workbook/{username}")
    public void executeWorkbook(
            @PathVariable(value = "username") String username,
            HttpServletResponse response
    ) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment: filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.executionService.executeWorkbook(response , username);
    }
}
