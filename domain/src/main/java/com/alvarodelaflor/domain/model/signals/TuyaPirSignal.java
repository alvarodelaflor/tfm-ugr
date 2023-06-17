package com.alvarodelaflor.domain.model.signals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TuyaPirSignal implements Serializable {
    private String deviceId;
    private LocalDateTime dateTime;
    private LocalDateTime disarmedUntil;
    private Status status;

    public enum Status {
        PIR, ARMING, DISARM, WAS_OFF;
    }

    public static Status getStatus(String value) {
        switch (value) {
            case "true":
                return Status.ARMING;
            case "false":
                return Status.DISARM;
            case "pir":
                return Status.PIR;
            case "30":
                return Status.WAS_OFF;
            default:
                return null;
        }
    }
}
