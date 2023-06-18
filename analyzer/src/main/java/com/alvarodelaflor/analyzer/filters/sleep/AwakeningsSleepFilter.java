package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.AwakeningsAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AwakeningsSleepFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        Long awakeningsTime = calculateAwakeTime(signal);
        Long numberOfAwakeningInterruptions = getNumberOfAwakeningInterruptions(signal);
        return signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && (awakeningsTime > 20 || numberOfAwakeningInterruptions > 3) ? Optional.of(getCommonAlert(awakeningsTime, numberOfAwakeningInterruptions, signal)) : Optional.empty();
    }

    public CommonAlert getCommonAlert(Long awakeningsTime, Long numberOfAwakeningInterruptions, Signal signal) {
        return AwakeningsAlert.builder()
                .numberOfAwakeningInterruptions(numberOfAwakeningInterruptions)
                .duration(awakeningsTime)
                .interruptionPeriods(getInterruptions(signal))
                .build();
    }

    private Long calculateAwakeTime(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .filter(sleepStageListEntry -> sleepStageListEntry.getKey().equals(SamsungWearSignal.SleepStage.AWAKE))
                .flatMap(sleepStageListEntry -> sleepStageListEntry.getValue().stream()
                        .map(sleepInterruption -> Duration.between(sleepInterruption.getStart(), sleepInterruption.getEnd()).toMinutes()))
                .reduce(Long::sum)
                .get();

    }

    private Long getNumberOfAwakeningInterruptions(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .filter(sleepStageListEntry -> sleepStageListEntry.getKey().equals(SamsungWearSignal.SleepStage.AWAKE))
                .flatMap(x -> x.getValue().stream())
                .count();
    }

    private List<SamsungWearSignal.SleepInterruption> getInterruptions(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getSleepPhases().entrySet().stream()
                .filter(sleepStageListEntry -> sleepStageListEntry.getKey().equals(SamsungWearSignal.SleepStage.AWAKE))
                .flatMap(sleepStageListEntry -> sleepStageListEntry.getValue().stream())
                .collect(Collectors.toList());
    }
}
