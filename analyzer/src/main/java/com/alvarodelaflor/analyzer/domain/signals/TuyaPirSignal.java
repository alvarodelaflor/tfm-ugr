package com.alvarodelaflor.analyzer.domain.signals;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TuyaPirSignal implements Serializable {
    private String deviceId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
