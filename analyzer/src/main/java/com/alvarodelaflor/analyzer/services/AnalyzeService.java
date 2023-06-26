package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.services.filters.MovementAnalyzerService;
import com.alvarodelaflor.analyzer.services.filters.SleepAnalyzerService;
import com.alvarodelaflor.analyzer.services.filters.VitalSignsAnalyzerService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.domain.model.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyzeService {

    @Autowired
    RedisService redisService;
    @Autowired
    SleepAnalyzerService sleepAnalyzerService;
    @Autowired
    VitalSignsAnalyzerService vitalSignsAnalyzerService;
    @Autowired
    MovementAnalyzerService movementAnalyzerService;
    @Autowired
    ValueService valueService;

    public List<Workbook> analyzeSignals(List<Signal> signals, String username) {
        List<Workbook> workbookList = new ArrayList<>();
        signals.stream().forEach(signal -> {
            Optional<Workbook> workbook = checkSignal(signal);
            if (workbook.isPresent()) {
                redisService.saveWorkbook(workbook.get(), username);
                workbookList.add(workbook.get());
                redisService.deleteSignal(signal.getId(), username);
            } else {
                // TODO Error msg
            }
        });

        return workbookList;
    }

    private Optional<Workbook> checkSignal(Signal signal) {
        List<CommonAlert> commonAlerts = new ArrayList<>();
        commonAlerts.addAll(sleepAnalyzerService.isAllRulesValid(signal, valueService));
        commonAlerts.addAll(vitalSignsAnalyzerService.isAllRulesValid(signal, valueService));
        commonAlerts.addAll(movementAnalyzerService.isAllRulesValid(signal, valueService));

        Workbook workbook = Workbook.builder()
                .id(signal.getId())
                .commonAlerts(commonAlerts)
                .build();

        return workbook == null ? Optional.empty() : Optional.of(workbook);
    }
}
