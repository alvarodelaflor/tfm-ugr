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

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AwakeningsAlert extends CommonAlert implements Serializable {

    Long numberOfAwakeningInterruptions;
    List<SamsungWearSignal.SleepInterruption> interruptionPeriods;
    Long duration;
    @Builder.Default
    Double weight = 1.1;
    @Builder.Default
    String link = "https://scielo.isciii.es/scielo.php?pid=S1137-66272007000200014&script=sci_arttext&tlng=en";
    @Builder.Default
    String summary = "En la enfermedad de Alzheimer (EA), el sueño se caracteriza por un aumento de los despertares, tanto en duración como en frecuencia";
    @Builder.Default
    String descriptionName = "Despertares continuos";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);

    String customText = "";

    public String getCustomText() {
        return "El usuario ha estado despierto (mientras intentaba quedarse dormido), durante un total de " + duration + " minutos distribuidos a lo largo de un total de " + numberOfAwakeningInterruptions +
                " interrupciones, por lo que la media de tiempo de interrupción por cada despertar ha sido de " + getAwakeningTimeAverage() + " minutos. A continuación se muestran los períodos de tiempo en los que el usuario ha estado despierto intentando volver a dormir: "
                + getInterruptionPeriods() + ".";
    }

    public Double getAwakeningTimeAverage() {
        double avg = duration * 1. / numberOfAwakeningInterruptions;
        return Math.round(avg * 100.0) / 100.0;
    }

}
