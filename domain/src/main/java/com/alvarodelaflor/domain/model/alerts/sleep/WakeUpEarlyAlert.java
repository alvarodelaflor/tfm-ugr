package com.alvarodelaflor.domain.model.alerts.sleep;

import com.alvarodelaflor.domain.model.alerts.AlertType;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WakeUpEarlyAlert extends CommonAlert implements Serializable {

    LocalDateTime lastSleepPhase;
    @Builder.Default
    Double weight = 0.9;
    @Builder.Default
    String link = "https://scielo.isciii.es/scielo.php?script=sci_arttext&pid=S1137-66272007000200011";
    @Builder.Default
    String summary = "Trastorno del ritmo circadiano. EL sujeto se despierta espontáneamente a primeras horas de la madrugada";
    @Builder.Default
    String descriptionName = "Despertar demasiado temprano";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);

    String customText = "";

    public String getCustomText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Particularmente este usuario se ha despertado el " + lastSleepPhase.format(formatter).replace(" ", " a las ") + ".";
    }
}
