package com.alvarodelaflor.sensors.domain.signals;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TuyaPirSignal {
    private String deviceId;
    private LocalDateTime dateTime;
    private LocalDateTime disarmedUntil;
}
