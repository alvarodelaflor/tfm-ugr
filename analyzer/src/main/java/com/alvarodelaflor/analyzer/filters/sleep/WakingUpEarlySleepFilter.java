package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.WakeUpEarlyAlert;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class WakingUpEarlySleepFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        Optional<CommonAlert> res;
        if (signal.getSamsungWearSignals() != null && signal.getSamsungWearSignals().getSleepSession() != null) {
            Optional<LocalDateTime> lastSleepPhase = getLastHourRecord(signal);
            res = signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && lastSleepPhase.isPresent() && lastSleepPhase.get().getHour() > valueService.getHour() ? Optional.empty() : Optional.of(getCommonAlert(lastSleepPhase.get()));
        } else {
            res = Optional.empty();
        }
        return res;
    }

    public CommonAlert getCommonAlert(LocalDateTime lastSleepPhase) {
        return WakeUpEarlyAlert.builder()
                .lastSleepPhase(lastSleepPhase)
                .build();
    }

    private Optional<LocalDateTime> getLastHourRecord(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .flatMap(sleepStageListEntry -> sleepStageListEntry.getValue().stream()
                        .map(sleepInterruption -> sleepInterruption.getEnd()))
                        .sorted(Comparator.reverseOrder())
                        .findFirst();
    }
}
