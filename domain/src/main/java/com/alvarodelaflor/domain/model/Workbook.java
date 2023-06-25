package com.alvarodelaflor.domain.model;

import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.alerts.movement.DisableSensorsMovementAlert;
import com.alvarodelaflor.domain.model.alerts.movement.RepeatedMovementAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.AwakeningsAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.DayTimeAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.RemAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.WakeUpEarlyAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.BradycardiaAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.LowBloodPressureAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@RedisHash("Workbook")
public class Workbook implements Serializable {

    private String id;
    private List<CommonAlert> commonAlerts;

    public static Workbook aBuilder() {
        return Workbook.builder()
                .commonAlerts(
                        Arrays.asList(
                                DisableSensorsMovementAlert
                                        .builder()
                                        .dateTimeList(Map.of("Lo que sea", Arrays.asList(LocalDateTime.now(), LocalDateTime.now())))
                                        .numberOfTimes(2)
                                        .build(),
                                RepeatedMovementAlert
                                        .builder()
                                        .repeatedMovementNumber(2)
                                        .repeatedMovement(Map.of("Salón", Arrays.asList(LocalDateTime.now(), LocalDateTime.now())))
                                        .build(),
                                AwakeningsAlert
                                        .builder()
                                        .interruptionPeriods(Arrays.asList(
                                                SamsungWearSignal.SleepInterruption
                                                        .builder()
                                                        .start(LocalDateTime.now())
                                                        .end(LocalDateTime.now().plusMinutes(40l))
                                                        .build(),
                                                SamsungWearSignal.SleepInterruption
                                                        .builder()
                                                        .start(LocalDateTime.now().plusMinutes(60l))
                                                        .end(LocalDateTime.now().plusMinutes(100l))
                                                        .build()
                                        ))
                                        .duration(80l)
                                        .numberOfAwakeningInterruptions(2l)
                                        .build(),
                                DayTimeAlert
                                        .builder()
                                        .daytimeSleepStages(
                                                Map.of(
                                                        SamsungWearSignal.SleepStage.LIGHT,
                                                        Arrays.asList(
                                                                SamsungWearSignal.SleepInterruption
                                                                        .builder()
                                                                        .start(LocalDateTime.now())
                                                                        .end(LocalDateTime.now().plusMinutes(20))
                                                                        .build(),
                                                                SamsungWearSignal.SleepInterruption
                                                                        .builder()
                                                                        .start(LocalDateTime.now().plusMinutes(40))
                                                                        .end(LocalDateTime.now().plusMinutes(60))
                                                                        .build()
                                                        ), SamsungWearSignal.SleepStage.REM,
                                                        Arrays.asList(
                                                                SamsungWearSignal.SleepInterruption
                                                                        .builder()
                                                                        .start(LocalDateTime.now().plusMinutes(80))
                                                                        .end(LocalDateTime.now().plusMinutes(81))
                                                                        .build())
                                                )
                                        )
                                        .duration(80l)
                                        .build(),
                                RemAlert
                                        .builder()
                                        .duration(6l)
                                        .build(),
                                WakeUpEarlyAlert
                                        .builder()
                                        .lastSleepPhase(LocalDateTime.now())
                                        .build(),
                                BradycardiaAlert
                                        .builder()
                                        .avgPulse(56.)
                                        .build(),
                                HighBloodPressureAlert
                                        .builder()
                                        .bloodPressure(SamsungWearSignal.BloodPresure
                                                .builder()
                                                .systolicPressure(150d)
                                                .diastolicPressure(90d)
                                                .build())
                                        .build(),
                                LowBloodPressureAlert
                                        .builder()
                                        .bloodPressure(SamsungWearSignal.BloodPresure
                                                .builder()
                                                .systolicPressure(90d)
                                                .diastolicPressure(70d)
                                                .build())
                                        .build()
                        ))
                .build();
    }
}
