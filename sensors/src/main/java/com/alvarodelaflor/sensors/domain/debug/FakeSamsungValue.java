package com.alvarodelaflor.sensors.domain.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FakeSamsungValue {
    ALL_BAD("allb"),
    DEFAULT("default");

    private String value;

    FakeSamsungValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FakeSamsungValue fromValue(String value) {
        for (FakeSamsungValue signal : values()) {
            if (signal.getValue().equalsIgnoreCase(value)) {
                return signal;
            }
        }
        return null;
    }
}