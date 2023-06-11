package com.alvarodelaflor.sensors.domain.signals;

import java.util.List;

public class SamsungWearSignal {

    private Long allSteps;
    private Double bloodGlucose; // measure metric: mm/dl
    private Double bloodOxygenSaturation;
    private BloodPresure bloodPresure;
    private Double avgPulse;
    private List<ExerciseSession> exerciseSession;

    public class BloodPresure {
        private Double systolicPressure; // mmHg
        private Double diastolicPressure; // mmHg
    }

    public class ExerciseSession {
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
