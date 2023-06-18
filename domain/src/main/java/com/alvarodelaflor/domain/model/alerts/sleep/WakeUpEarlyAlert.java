package com.alvarodelaflor.domain.model.alerts.sleep;

import com.alvarodelaflor.domain.model.alerts.SleepCommonAlert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WakeUpEarlyAlert extends SleepCommonAlert implements Serializable {

    LocalDateTime lastSleepPhase;
    @Builder.Default
    Double weight = 0.9;
    @Builder.Default
    String name = "WAKE_UP_EARLY_SLEEP_FILTER";;
    @Builder.Default
    String link = "https://scielo.isciii.es/scielo.php?script=sci_arttext&pid=S1137-66272007000200011";
    @Builder.Default
    String summary = "Trastorno del ritmo circadiano. EL sujeto se despierta espontáneamente a primeras horas de la madrugada";
}
