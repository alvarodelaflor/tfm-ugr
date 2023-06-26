package com.alvarodelaflor.analyzer.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ValueService {

    @Value("${remSleepFilter.remTime}")
    private Integer remTime;

    @Value("${disableSensorsMovementFilter.disableSensorsOccurrences}")
    private Integer disableSensorsOccurrences;

    @Value("${repeatedActionMovementFilter.maxMinutes}")
    private Integer maxMinutes;

    @Value("${repeatedActionMovementFilter.repeatNumber}")
    private Integer repeatNumber;

    @Value("${awakeningsSleepFilter.awakeningsTime}")
    private Integer awakeningsTime;

    @Value("${awakeningsSleepFilter.numberOfAwakeningInterruptions}")
    private Integer numberOfAwakeningInterruptions;

    @Value("${dayTimeNapsSleepFilter.duration}")
    private Integer duration;

    @Value("${wakingUpEarlySleepFilter.hour}")
    private Integer hour;

    @Value("${bradycardiaVitalSignFilter.avgPulse}")
    private Double avgPulse;

    @Value("${highBloodPressureVitalSignFilter.diastolicPressure}")
    private Double hDiastolicPressure;

    @Value("${highBloodPressureVitalSignFilter.systolicPressure}")
    private Double hSystolicPressure;

    @Value("${lowBloodPressureVitalSignFilter.diastolicPressure}")
    private Double lDiastolicPressure;

    @Value("${lowBloodPressureVitalSignFilter.systolicPressure}")
    private Double lSystolicPressure;
}
