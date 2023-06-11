package com.alvarodelaflor.sensors.domain.signals;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SamsungWearSignal {

    private Long allSteps;
    private Double bloodGlucose; // measure metric: mm/dl
    private Double bloodOxygenSaturation;
    private BloodPresure bloodPresure;
    private Double avgPulse;
    private List<ExerciseSession> exerciseSession;

    @Builder
    @Getter
    public static class BloodPresure {
        private Double systolicPressure; // mmHg
        private Double diastolicPressure; // mmHg
    }

    @Builder
    @Getter
    public static class ExerciseSession {
        private String activitiyName;
        private Integer caloriesBurned;
        private Double distanceDone; // meters
        private Double avgPulse;
        private Double maxPulse;
        private Double minPulse;
        private Double maxSpeed; // km/h
        private Double avgSpeed; // km/h

    }

}
