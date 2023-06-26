package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.alerts.AlertType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private static final Logger logger = LoggerFactory.getLogger(ActionService.class);

    public void executeAction(Workbook workbook) {
        workbook.getCommonAlerts().stream()
                .filter(alert -> alert.getAlertType().contains(AlertType.ACTION))
                .forEach(alert-> {
                    // TODO
                    logger.info(String.format("EXECUTE ACTION: %s", alert.getSummary()));
                });
    }
}
