package com.alvarodelaflor.analyzer.filters.movement;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.analyzer.services.ValueService;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.movement.DisableSensorsMovementAlert;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisableSensorsMovementFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal, ValueService valueService) {
        Map<String, List<LocalDateTime>> disableSensorsOccurrences = getDisableSensorsOccurrences(signal);
        Integer numberOfTimes = getNumberOfOccurrences(disableSensorsOccurrences);
        return numberOfTimes < valueService.getDisableSensorsOccurrences() ? Optional.empty() : Optional.of(getCommonAlert(disableSensorsOccurrences, numberOfTimes));
    }

    public CommonAlert getCommonAlert(Map<String, List<LocalDateTime>> disableSensorsOccurrences, Integer numberOfTimes) {
        return DisableSensorsMovementAlert.builder()
                .dateTimeList(disableSensorsOccurrences)
                .numberOfTimes(numberOfTimes)
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
