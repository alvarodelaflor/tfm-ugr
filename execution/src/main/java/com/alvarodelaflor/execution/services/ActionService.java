package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.alerts.AlertType;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    public void executeAction(Workbook workbook) {
        workbook.getCommonAlerts().stream()
                .filter(alert -> alert.getAlertType().contains(AlertType.ACTION))
                .forEach(alert-> {
                    // TODO
                    System.out.println("Execute: " + alert.getSummary());
                });
    }
}
