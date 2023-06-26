package com.alvarodelaflor.domain.model.alerts.sleep;

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
import java.util.Map;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayTimeAlert extends CommonAlert implements Serializable {

    Long duration;
    Map<SamsungWearSignal.SleepStage, List<SamsungWearSignal.SleepInterruption>> daytimeSleepStages;
    @Builder.Default
    Double weight = 1.6;
    @Builder.Default
    String link = "https://scielo.isciii.es/scielo.php?pid=S1137-66272007000200014&script=sci_arttext&tlng=en";
    @Builder.Default
    String summary = "En la enfermedad de Alzheimer (EA), el sueño se caracteriza por un aumento de las siestas diurnas";
    @Builder.Default
    String descriptionName = "Siestas diurnas";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);

    String customText = "";

    public String getCustomText() {
        return "El usuario ha durmiendo, durante el día, un total de " + duration + " minutos. Por cada sueño que ha realizado durante el día, se muestran las etapas que a atravesado para cada uno de ellos: " + daytimeSleepStages + ".";
    }
}
