package com.alvarodelaflor.domain.model.signals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SamsungWearSignal implements Serializable {

    private Long allSteps;
    private Double bloodGlucose; // measure metric: mm/dl
    private Double bloodOxygenSaturation;
    private BloodPresure bloodPresure;
    private Double avgPulse;
    private List<ExerciseSession> exerciseSession;
    private SleepSession sleepSession;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BloodPresure implements Serializable {
        private Double systolicPressure; // mmHg
        private Double diastolicPressure; // mmHg
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseSession implements Serializable{
        private String activitiyName;
        private Integer caloriesBurned;
        private Double distanceDone; // meters
        private Double avgPulse;
        private Double maxPulse;
        private Double minPulse;
        private Double maxSpeed; // km/h
        private Double avgSpeed; // km/h

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SleepSession implements Serializable{
        private Map<SleepStage, List<SleepInterruption>> sleepPhases;
        private Double bloodOxygenSaturation;
        private Double avgPulse;
        private Boolean fullDayRecord;

        public Long getTotalSleepDuration() {
            return this.sleepPhases.entrySet().stream()
                    .filter(sleepStage -> !sleepStage.getKey().equals(SleepStage.AWAKE))
                    .flatMap(x -> x.getValue().stream().map(y -> Duration.between(y.getStart(), y.getEnd()).toMinutes()))
                    .reduce(Long::sum)
                    .get();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SleepInterruption implements Serializable{
        private LocalDateTime start;
        private LocalDateTime end;

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return "interrupción con fecha de inicio: " + start.format(formatter) + " y fecha de fin " + end.format(formatter);
        }
    }

    public static enum SleepStage {
        AWAKE, LIGHT, DEEP, REM
    }
}
