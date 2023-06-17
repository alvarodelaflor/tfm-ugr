package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.domain.model.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnalyzeService {

    @Autowired
    RedisService redisService;
    @Autowired
    SleepAnalyzerService sleepAnalyzerService;

    public List<Workbook> analyzeSignals(List<Signal> signals, String username) {
        List<Workbook> workbookList = new ArrayList<>();
        signals.stream().forEach(signal -> {
            Optional<Workbook> workbook = checkSignal(signal);
            if (workbook.isPresent()) {
                workbookList.add(workbook.get());
                redisService.deleteSignal(signal.getId(), username);
            } else {
                // Lanzar log y mensaje de error
            }
        });

        return workbookList;
    }

    private Optional<Workbook> checkSignal(Signal signal) {
        Workbook workbook = Workbook.builder()
                .sleepAlert(Workbook.SleepAlert.builder().reemSleep(sleepAnalyzerService.isAllRulesValid(signal)).build())
                .build();

        return workbook == null ? Optional.empty() : Optional.of(workbook);
    }
}
