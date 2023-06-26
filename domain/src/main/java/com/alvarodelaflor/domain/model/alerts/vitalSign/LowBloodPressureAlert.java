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
public class LowBloodPressureAlert extends CommonAlert implements Serializable {

    SamsungWearSignal.BloodPresure bloodPressure;
    @Builder.Default
    Double weight = 2.9;
    @Builder.Default
    String link = "https://www.nhlbi.nih.gov/es/salud/presion-arterial-baja";
    @Builder.Default
    String summary = "Hipotensión. La prensión sanguínea es más baja de lo normal, podría ser una bajada de tensión repentina grave";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.ACTION);
    @Builder.Default
    String descriptionName = "Hipotensión";

    String customText = "";

    public String getCustomText() {
        return "El usuario estudiado tiene una una presión de " + bloodPressure.getSystolicPressure() + " mmHG para la sistólica y de " + bloodPressure.getDiastolicPressure() + " mmHg para la diástolica.";
    }
}
