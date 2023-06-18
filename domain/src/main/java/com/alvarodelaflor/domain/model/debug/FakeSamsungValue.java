package com.alvarodelaflor.domain.model.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FakeSamsungValue {
    ALL_BAD("allb"),
    SLEEP("sleep"),
    LOW_BLOOD_PRESSURE("lbp"),
    DEFAULT("default");

    private String value;

    FakeSamsungValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<FakeSamsungValue> fromValue(String value) {
        List<FakeSamsungValue> fakeSamsungValues = new ArrayList<>();
        if (value!=null) {
            for (String temp : Arrays.asList(value.split(","))) {
                FakeSamsungValue fakeSamsungValue = fromString(temp);
                if (fakeSamsungValue != null) {
                    fakeSamsungValues.add(fakeSamsungValue);
                }
            }
        }

        return fakeSamsungValues;
    }

    private static FakeSamsungValue fromString(String input) {
        for (FakeSamsungValue value : FakeSamsungValue.values()) {
            if (value.getValue().equalsIgnoreCase(input)) {
                return value;
            }
        }
        return null; // Si no se encuentra ninguna coincidencia
    }
}