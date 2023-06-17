package com.alvarodelaflor.analyzer.filters.sleep;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;

public class RemSleepFilter implements Filter {

    static String name = "REM_SLEEP_FILTER";
    static Double weight = 15.;

    @Override
    public Boolean isRuleValid(Signal signal) {
        return signal.getSamsungWearSignals().getSleepSession().getFullDayRecord() && calculateRemTime(signal) > 30 ? true : false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getWeight() {
        return weight;
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
