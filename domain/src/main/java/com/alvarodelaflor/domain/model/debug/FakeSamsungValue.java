package com.alvarodelaflor.domain.model.debug;

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