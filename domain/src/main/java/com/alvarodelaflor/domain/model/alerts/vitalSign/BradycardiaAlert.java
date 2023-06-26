package com.alvarodelaflor.domain.model.alerts.vitalSign;

import com.alvarodelaflor.domain.model.alerts.AlertType;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class BradycardiaAlert extends CommonAlert implements Serializable {

    Double avgPulse;
    @Builder.Default
    Double weight = 1.6;
    @Builder.Default
    String link = "https://www.sciencedirect.com/science/article/pii/S0213485313000558";
    @Builder.Default
    String summary = "La bradicardia (< 60 ppm) es estadísticamente más frecuente en los pacientes con DFT (demencia frontotemporal)";
    @Builder.Default
    String descriptionName = "Bradicardia";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM, AlertType.ACTION);

    String customText = "";

    public String getCustomText() {
        return "Para este usuario en concreto, ha tenido de media unas " + avgPulse.toString().replace(".", ",") + " pulsaciones por minuto.";
    }
}
