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
    CommonAlertName name = CommonAlertName.AWAKENINGS_SLEEP_FILTER;
    @Builder.Default
    String link = "https://scielo.isciii.es/scielo.php?pid=S1137-66272007000200014&script=sci_arttext&tlng=en";
    @Builder.Default
    String summary = "En la enfermedad de Alzheimer (EA), el sueño se caracteriza por un aumento de los despertares, tanto en duración como en frecuencia";
    @Builder.Default
    String descriptionName = "Despertares continuos";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
    @Builder.Default
    String customText = "";

    public Double getAwakeningTimeAverage() {
        return duration * 1. / numberOfAwakeningInterruptions;
    }

}
