package com.alvarodelaflor.domain.model.alerts.vitalSign;

import com.alvarodelaflor.domain.model.alerts.AlertType;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import com.alvarodelaflor.domain.model.signals.SamsungWearSignal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HighBloodPressureAlert extends CommonAlert implements Serializable {

    SamsungWearSignal.BloodPresure bloodPressure;
    @Builder.Default
    Double weight = 2.9;
    @Builder.Default
    String link = "https://www.sciencedirect.com/science/article/abs/pii/S1853002810700707";
    @Builder.Default
    String summary = "Los pacientes con hipertensión arterial muestran más deterioro cognitivo que los controles normales en diferentes estudios";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM, AlertType.ACTION);
    @Builder.Default
    String descriptionName = "Hipertensión";

    String customText = "";

    public String getCustomText() {
        return "El usuario estudiado tiene una una presión de " + bloodPressure.getSystolicPressure() + " mmHG para la sistólica y de " + bloodPressure.getDiastolicPressure() + " mmHg para la diástolica.";
    }
}
