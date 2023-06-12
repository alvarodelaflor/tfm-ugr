package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.sensors.domain.debug.FakeSamsungValue;
import com.alvarodelaflor.sensors.domain.debug.FakeSignal;
import com.alvarodelaflor.sensors.domain.signals.Signal;
import com.alvarodelaflor.sensors.repository.SignalDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeviceService {

    @Autowired
    TuyaDeviceService tuyaDeviceService;
    @Autowired
    SamsungDeviceService samsungDeviceService;

    @Autowired
    SignalDao signalDao;

    public Signal getAndSaveAllDeviceSignals(LocalDateTime startDateTime, LocalDateTime endDateTime, List<FakeSignal> debug, FakeSamsungValue fakeSamsungValue) {
        Signal signal = Signal.builder()
                .id(startDateTime.toEpochSecond(OffsetDateTime.now().getOffset()) + "-" + endDateTime.toEpochSecond(OffsetDateTime.now().getOffset()))
                .tuyaPirSignals(this.tuyaDeviceService.getPirSignals(startDateTime, endDateTime))
                .samsungWearSignals(debug.contains(FakeSignal.SAMSUNG) ? this.samsungDeviceService.getSignalFake(startDateTime, endDateTime, fakeSamsungValue) : this.samsungDeviceService.getSignal(startDateTime, endDateTime))
                .build();
        signalDao.save(signal);

        return signal;
    }

    public List<Signal> getAllSignalRecordsRedis() {
        return signalDao.findAll();
    }
}
