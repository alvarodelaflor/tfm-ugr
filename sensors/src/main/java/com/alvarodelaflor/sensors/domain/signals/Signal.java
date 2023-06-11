package com.alvarodelaflor.sensors.domain.signals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Signal {
    private SamsungWearSignal samsungWearSignals;
    private List<TuyaPirSignal> tuyaPirSignals;
}
