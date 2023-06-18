package com.alvarodelaflor.domain.model.alerts.sleep;

import com.alvarodelaflor.domain.model.alerts.CommonAlert;
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
public class BradycardiaAlert extends CommonAlert implements Serializable {

    Double avgPulse;
    @Builder.Default
    Double weight = 1.6;
    @Builder.Default
    String name = "BRADYCARDIA_VITAL_SIGN_FILTER";;
    @Builder.Default
    String link = "https://www.sciencedirect.com/science/article/pii/S0213485313000558";
    @Builder.Default
    String summary = "La bradicardia (< 60 ppm) es estadísticamente más frecuente en los pacientes con DFT (Demencia frontotemporal)";
}
