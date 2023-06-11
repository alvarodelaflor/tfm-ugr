package com.alvarodelaflor.sensors.domain.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FakeSignal {
    SAMSUNG("samsung"),
    TUYA_PIR("tuyap");

    private String value;

    FakeSignal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<FakeSignal> fromDebugParam(String debugParam) {
        if (debugParam == null || debugParam.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> debugValues = Arrays.asList(debugParam.split(","));
        return debugValues.stream()
                .map(String::trim)
                .map(FakeSignal::fromValue)
                .filter(signal -> signal != null)
                .collect(Collectors.toList());
    }

    public static FakeSignal fromValue(String value) {
        for (FakeSignal signal : values()) {
            if (signal.getValue().equalsIgnoreCase(value)) {
                return signal;
            }
        }
        return null;
    }
}