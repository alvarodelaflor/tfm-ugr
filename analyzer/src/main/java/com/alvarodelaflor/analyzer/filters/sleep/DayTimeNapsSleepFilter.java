package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.DayTimeAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayTimeNapsSleepFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        Optional<CommonAlert> res;
        if (signal.getSamsungWearSignals() != null && signal.getSamsungWearSignals().getSleepSession() != null) {
            Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> dayTimeNap = calculateDaytimeSleepSession(signal);
            Long duration = calculateTotalDuration(dayTimeNap);
            res = signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && duration < valueService.getDuration() ? Optional.empty() : Optional.of(getCommonAlert(dayTimeNap, duration));
        } else {
            res = Optional.empty();
        }
        return res;
    }

    public CommonAlert getCommonAlert(Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> dayTimeNap, Long duration) {
        return DayTimeAlert.builder()
                .daytimeSleepStages(dayTimeNap)
                .duration(duration)
                .build();
    }

    private Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> calculateDaytimeSleepSession(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .filter(sleepStageListEntry -> sleepStageListEntry.getValue().stream().anyMatch(sleepInterruption -> isNapTime(sleepInterruption)))
                .collect(Collectors.toMap(sleep -> sleep.getKey(), sleep -> sleep.getValue().stream().filter(interruption -> isNapTime(interruption)).collect(Collectors.toList())));
    }

    private Long calculateTotalDuration(Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> sleepSessions) {
        return sleepSessions.values().stream()
                .flatMap(session -> session.stream().map(interruption -> Duration.between(interruption.getStart(), interruption.getEnd()).toMinutes()))
                .reduce(Long::sum)
                .orElse(0l);
    }

    private Boolean isNapTime(SamsungWearSignal.SleepInterruption sleepInterruption) {
        return sleepInterruption.getStart().getHour() >= 12 & sleepInterruption.getStart().getHour() < 21;
    }
}
