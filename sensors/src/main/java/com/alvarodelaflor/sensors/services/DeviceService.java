package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.sensors.domain.debug.FakeSamsungValue;
import com.alvarodelaflor.sensors.domain.debug.FakeSignal;
import com.alvarodelaflor.sensors.domain.signals.Signal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeviceService {

    @Autowired
    TuyaDeviceService tuyaDeviceService;
    @Autowired
    SamsungDeviceService samsungDeviceService;

    public Signal getAllDeviceSignals(LocalDateTime startDateTime, LocalDateTime endDateTime, List<FakeSignal> debug, FakeSamsungValue fakeSamsungValue) {
        return Signal.builder()
                .tuyaPirSignals(this.tuyaDeviceService.getPirSignals(startDateTime, endDateTime))
                .samsungWearSignals(debug.contains(FakeSignal.SAMSUNG) ? this.samsungDeviceService.getSignalFake(startDateTime, endDateTime, fakeSamsungValue) : this.samsungDeviceService.getSignal(startDateTime, endDateTime))
                .build();
    }
}
