package com.alvarodelaflor.analyzer.filters.movement;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.movement.DisableSensorsMovementAlert;
import com.alvarodelaflor.domain.model.alerts.movement.RepeatedMovementAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DisableSensorsMovementFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        Map<String, List<LocalDateTime>> disableSensorsOccurrences = getDisableSensorsOccurrences(signal);
        return disableSensorsOccurrences.size() < 1 ? Optional.empty() : Optional.of(getCommonAlert(disableSensorsOccurrences));
    }

    public CommonAlert getCommonAlert(Map<String, List<LocalDateTime>> disableSensorsOccurrences) {
        return DisableSensorsMovementAlert.builder()
                .dateTimeList(disableSensorsOccurrences)
                .numberOfTimes(getNumberOfOccurrences(disableSensorsOccurrences))
                .build();
    }

    public Map<String, List<LocalDateTime>> getDisableSensorsOccurrences(Signal signal) {
        return signal.getTuyaPirSignals().stream()
                .filter(tuyaSignal -> tuyaSignal.getStatus().equals(TuyaPirSignal.Status.WAS_OFF))
                .collect(Collectors.groupingBy(TuyaPirSignal::getDeviceId, Collectors.mapping(TuyaPirSignal::getDateTime, Collectors.toList())));
    }

    public Integer getNumberOfOccurrences(Map<String, List<LocalDateTime>> disableSensorsOccurrences) {
        return disableSensorsOccurrences.values().stream()
                .map(disableDateTimeList -> disableDateTimeList.size())
                .reduce(Integer::sum)
                .orElse(0);
    }
}
