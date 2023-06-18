package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.domain.model.debug.FakeSamsungValue;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SamsungDeviceService {

    @Autowired
    private RandomService randomService;

    public SamsungWearSignal getSignalFake(LocalDateTime startDateTime, LocalDateTime endDateTime, List<FakeSamsungValue> fakeSamsungValue) {
        if (fakeSamsungValue.contains(FakeSamsungValue.ALL_BAD)) {
            return buildFakeSignalNoNormal(startDateTime, endDateTime, fakeSamsungValue.contains(FakeSamsungValue.SLEEP));
        } else {
            return buildFakeSignalNormal(startDateTime , endDateTime, fakeSamsungValue.contains(FakeSamsungValue.SLEEP));
        }
    }

    public SamsungWearSignal getSignal(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

    private SamsungWearSignal buildFakeSignalNormal(LocalDateTime startDateTime, LocalDateTime endDateTime, Boolean hasSleepSession) {
        SamsungWearSignal.SamsungWearSignalBuilder builder =  SamsungWearSignal.builder()
                .allSteps(randomService.getRandomLong(0l, 100l))
                .bloodGlucose(randomService.getRandomDouble(70.0, 100.000))
                .bloodOxygenSaturation(randomService.getRandomDouble(95.0, 100.000))
                .bloodPresure(SamsungWearSignal.BloodPresure.builder()
                        .systolicPressure(randomService.getRandomDouble(110.0, 120.000))
                        .diastolicPressure(randomService.getRandomDouble(70.0, 80.000))
                        .build())
                .avgPulse(randomService.getRandomDouble(60.0, 100.000))
                .exerciseSession(null);
        if (hasSleepSession) {
            Integer startAwake = randomService.getRandomInteger(1, 15);
            Integer endAwake = randomService.getRandomInteger(startAwake, 20);
            Integer endLight = randomService.getRandomInteger(endAwake, 30);
            Integer endDeep = randomService.getRandomInteger(endLight, 30);

            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderAwake = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 23, startAwake))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 23, endAwake))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderLight = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 23, endAwake))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 4, endLight))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderDeep = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 4, endLight))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 6, endDeep))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderRem = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 6, endDeep))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 7, 0))
                    .build();

            Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> sleepPhases = Map.of(
                    SamsungWearSignal.SleepStage.AWAKE, Arrays.asList(sleepInterruptionBuilderAwake),
                    SamsungWearSignal.SleepStage.LIGHT, Arrays.asList(sleepInterruptionBuilderLight),
                    SamsungWearSignal.SleepStage.DEEP, Arrays.asList(sleepInterruptionBuilderDeep),
                    SamsungWearSignal.SleepStage.REM, Arrays.asList(sleepInterruptionBuilderRem)
            );

            SamsungWearSignal.SleepSession sleepSession = SamsungWearSignal.SleepSession.builder()
                    .sleepPhases(sleepPhases)
                    .avgPulse(randomService.getRandomDouble(60.000, 100.000))
                    .bloodOxygenSaturation(randomService.getRandomDouble(93.000, 100.000))
                    .fullDayRecord(true)
                    .build();

            builder.sleepSession(sleepSession);
        }
        return builder.build();
    }

    private SamsungWearSignal buildFakeSignalNoNormal(LocalDateTime startDateTime, LocalDateTime endDateTime, Boolean hasSleepSession) {
        SamsungWearSignal.SamsungWearSignalBuilder builder = SamsungWearSignal.builder()
                .allSteps(randomService.getRandomLong(5000l, 6000l))
                .bloodGlucose(randomService.getRandomDouble(30.0, 50.000))
                .bloodOxygenSaturation(randomService.getRandomDouble(70.0, 80.000))
                .bloodPresure(SamsungWearSignal.BloodPresure.builder()
                        .systolicPressure(randomService.getRandomDouble(140.0, 160.000))
                        .diastolicPressure(randomService.getRandomDouble(90.0, 110.000))
                        .build())
                .avgPulse(randomService.getRandomDouble(50., 59.990))
                .exerciseSession(null)
                .sleepSession(null);

        if (hasSleepSession) {

            SamsungWearSignal.SleepInterruption nap1 = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 12, 30))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 12, 42))
                    .build();

            SamsungWearSignal.SleepInterruption nap2 = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 15, 12))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 15, 15))
                    .build();

            Integer startAwake = randomService.getRandomInteger(1, 15);
            Integer endAwake = randomService.getRandomInteger(30, 59);
            Integer endLight = randomService.getRandomInteger(0, 30);
            Integer endDeep = randomService.getRandomInteger(endLight, 40);
            Integer endRem = randomService.getRandomInteger(endDeep, 58);

            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderAwake = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 23, startAwake))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 1, endAwake))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderLight = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 1, endAwake))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 5, endLight))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderDeep = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 5, endLight))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 5, endDeep))
                    .build();
            SamsungWearSignal.SleepInterruption sleepInterruptionBuilderRem = SamsungWearSignal.SleepInterruption.builder()
                    .start(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 5, endDeep))
                    .end(LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth() + 1, 5, endRem + 1))
                    .build();

            Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> sleepPhases = Map.of(
                    SamsungWearSignal.SleepStage.AWAKE, Arrays.asList(sleepInterruptionBuilderAwake, nap1, nap2),
                    SamsungWearSignal.SleepStage.LIGHT, Arrays.asList(sleepInterruptionBuilderLight),
                    SamsungWearSignal.SleepStage.DEEP, Arrays.asList(sleepInterruptionBuilderDeep),
                    SamsungWearSignal.SleepStage.REM, Arrays.asList(sleepInterruptionBuilderRem)
            );

            SamsungWearSignal.SleepSession sleepSession = SamsungWearSignal.SleepSession.builder()
                    .sleepPhases(sleepPhases)
                    .avgPulse(randomService.getRandomDouble(100.000, 120.000))
                    .bloodOxygenSaturation(randomService.getRandomDouble(83.000, 93.000))
                    .fullDayRecord(true)
                    .build();

            builder.sleepSession(sleepSession);
        }

        return builder.build();
    }
}
