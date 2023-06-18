package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.SleepCommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.RemAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.WakeUpEarlyAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class WakingUpEarlySleepFilter implements Filter {

    @Override
    public Optional<SleepCommonAlert> isRuleValid(Signal signal) {
        Optional<LocalDateTime> lastSleepPhase = getLastHourRecord(signal);
        return signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && lastSleepPhase.isPresent() && lastSleepPhase.get().getHour() > 6 ? Optional.empty() : Optional.of(getCommonAlert(lastSleepPhase.get()));
    }

    public SleepCommonAlert getCommonAlert(LocalDateTime lastSleepPhase) {
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
