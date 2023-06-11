package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.sensors.domain.debug.FakeSamsungValue;
import com.alvarodelaflor.sensors.domain.signals.SamsungWearSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SamsungDeviceService {

    @Autowired
    private RandomService randomService;

    public SamsungWearSignal getSignalFake(LocalDateTime startDateTime, LocalDateTime endDateTime, FakeSamsungValue fakeSamsungValue) {
        fakeSamsungValue = fakeSamsungValue == null ? FakeSamsungValue.DEFAULT : fakeSamsungValue;
        switch (fakeSamsungValue) {
            case ALL_BAD:
                return buildFakeSignalNoNormal();
            default:
                return buildFakeSignalNormal();
        }
    }

    public SamsungWearSignal getSignal(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

    private SamsungWearSignal buildFakeSignalNormal() {
        return SamsungWearSignal.builder()
                .allSteps(randomService.getRandomLong(0l, 100l))
                .bloodGlucose(randomService.getRandomDouble(70.0, 100.000))
                .bloodOxygenSaturation(randomService.getRandomDouble(95.0, 100.000))
                .bloodPresure(SamsungWearSignal.BloodPresure.builder()
                        .systolicPressure(randomService.getRandomDouble(110.0, 120.000))
                        .diastolicPressure(randomService.getRandomDouble(70.0, 80.000))
                        .build())
                .avgPulse(randomService.getRandomDouble(60.0, 100.000))
                .exerciseSession(null)
                .build();
    }

    private SamsungWearSignal buildFakeSignalNoNormal() {
        return SamsungWearSignal.builder()
                .allSteps(randomService.getRandomLong(5000l, 6000l))
                .bloodGlucose(randomService.getRandomDouble(30.0, 50.000))
                .bloodOxygenSaturation(randomService.getRandomDouble(70.0, 80.000))
                .bloodPresure(SamsungWearSignal.BloodPresure.builder()
                        .systolicPressure(randomService.getRandomDouble(140.0, 160.000))
                        .diastolicPressure(randomService.getRandomDouble(90.0, 110.000))
                        .build())
                .avgPulse(randomService.getRandomDouble(105.0, 170.000))
                .exerciseSession(null)
                .build();
    }
}
