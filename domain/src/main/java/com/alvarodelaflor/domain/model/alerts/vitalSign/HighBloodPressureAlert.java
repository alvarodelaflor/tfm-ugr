package com.alvarodelaflor.domain.model.alerts.vitalSign;

import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HighBloodPressureAlert extends CommonAlert implements Serializable {

    SamsungWearSignal.BloodPresure bloodPressure;
    @Builder.Default
    Double weight = 2.9;
    @Builder.Default
    String name = "HIGH_BLODD_PRESSURE_VITAL_SIGN_FILTER";;
    @Builder.Default
    String link = "https://www.sciencedirect.com/science/article/abs/pii/S1853002810700707";
    @Builder.Default
    String summary = "Los pacientes con hipertensión arterial muestran más deterioro cognitivo que los controles normales en diferentes estudios";
}
