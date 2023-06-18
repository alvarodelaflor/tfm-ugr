package com.alvarodelaflor.analyzer.filters.vitalSigns;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.BradycardiaAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;

import java.time.Duration;
import java.util.Optional;

public class BradycardiaVitalSignFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        Double avgPulse = signal.getSamsungWearSignals().getAvgPulse();
        return avgPulse > 60.0 ? Optional.empty() : Optional.of(getCommonAlert(avgPulse));
    }

    public CommonAlert getCommonAlert(Double avgPulse) {
        return BradycardiaAlert.builder()
                .avgPulse(avgPulse)
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
