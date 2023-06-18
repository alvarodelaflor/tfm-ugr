package com.alvarodelaflor.analyzer.filters.movement;

import com.alvarodelaflor.analyzer.filters.Filter;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.movement.RepeatedMovementAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RepeatedActionMovementFilter implements Filter {

    @Override
    public Optional<CommonAlert> isRuleValid(Signal signal) {
        Map<String, List<LocalDateTime>> repeatedMovement = getRepeatedMovement(signal, 3);

        return repeatedMovement.size() < 1 ? Optional.empty() : Optional.of(getCommonAlert(repeatedMovement));
    }

    public CommonAlert getCommonAlert(Map<String, List<LocalDateTime>> repeatedMovement) {
        return RepeatedMovementAlert.builder()
                .repeatedMovement(repeatedMovement)
                .repeatedMovementNumber(getRepeatedMovementNumber(repeatedMovement))
                .build();
    }

    private Integer getRepeatedMovementNumber(Map<String, List<LocalDateTime>> repeatedMovement) {
        return repeatedMovement.values().stream()
                .map(repeated -> repeated.size())
                .reduce(Integer::sum)
                .orElse(0);
    }

    private Map<String, List<LocalDateTime>> getRepeatedMovement(Signal signal, Integer maxMinutes) {
        Map<String, List<LocalDateTime>> movementBySensorId = getMovementBySensorId(signal);

        Map<String, List<LocalDateTime>> filteredMovements = new HashMap<>();
        for (String deviceId: movementBySensorId.keySet()) {
            List<LocalDateTime> closeMovements = new ArrayList<>();

            List<LocalDateTime> deviceMovements = movementBySensorId.get(deviceId).stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            for (int i = 0; i < deviceMovements.size() - 1; i++) {
                LocalDateTime movementToStudy1 = movementBySensorId.get(deviceId).get(i);
                LocalDateTime movementToStudy2 = movementBySensorId.get(deviceId).get(i + 1);
                if (Duration.between(movementToStudy1, movementToStudy2).toMinutes() < maxMinutes) {
                    closeMovements.add(movementToStudy1);
                    closeMovements.add(movementToStudy2);
                }
            }

            closeMovements = closeMovements.stream().collect(Collectors.toSet()).stream().sorted(Comparator.naturalOrder()).toList();
            filteredMovements.put(deviceId, closeMovements);
        }

        return filteredMovements;
    }

    private Map<String, List<LocalDateTime>> getMovementBySensorId(Signal signal) {
        return signal.getTuyaPirSignals().stream()
                .filter(signalEvent -> signalEvent.getStatus().equals(TuyaPirSignal.Status.PIR))
                .collect(Collectors.groupingBy(TuyaPirSignal::getDeviceId,
                        Collectors.mapping(TuyaPirSignal::getDateTime, Collectors.toList())));
    }
}
