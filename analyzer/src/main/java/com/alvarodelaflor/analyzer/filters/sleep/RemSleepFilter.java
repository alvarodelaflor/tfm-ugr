package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.RemAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RemSleepFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        Optional<CommonAlert> res;
        if (signal.getSamsungWearSignals() != null) {
            Long remTime = calculateRemTime(signal);
            res = signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && remTime >= valueService.getRemTime() ? Optional.empty() : Optional.of(getCommonAlert(remTime));
        } else {
            res = Optional.empty();
        }
        return res;
    }

    public CommonAlert getCommonAlert(Long remTime) {
        return RemAlert.builder()
                .duration(remTime)
                .build();
    }

    private Long calculateRemTime(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .filter(sleepStageListEntry -> sleepStageListEntry.getKey().equals(SamsungWearSignal.SleepStage.REM))
                .flatMap(sleepStageListEntry -> sleepStageListEntry.getValue().stream()
                        .map(sleepInterruption -> Duration.between(sleepInterruption.getStart(), sleepInterruption.getEnd()).toMinutes()))
                .reduce(Long::sum)
                .get();

    }
}
