package com.alvarodelaflor.analyzer.domain.signals;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class SamsungWearSignal implements Serializable {

    private Long allSteps;
    private Double bloodGlucose; // measure metric: mm/dl
    private Double bloodOxygenSaturation;
    private BloodPresure bloodPresure;
    private Double avgPulse;
    private List<ExerciseSession> exerciseSession;

    @Builder
    @Getter
    public static class BloodPresure implements Serializable {
        private Double systolicPressure; // mmHg
        private Double diastolicPressure; // mmHg
    }

    @Builder
    @Getter
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

}
