package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.RemAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;
import java.util.Optional;

public class RemSleepFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        Long remTime = calculateRemTime(signal);
        return signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && remTime > 30 ? Optional.empty() : Optional.of(getCommonAlert(remTime));
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
