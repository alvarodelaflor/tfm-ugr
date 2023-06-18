package com.alvarodelaflor.domain.model.Alerts.sleep;

import com.alvarodelaflor.domain.model.Alerts.SleepCommonAlert;
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
public class RemAlert extends SleepCommonAlert implements Serializable {

    Long duration;
    @Builder.Default
    Double weight = 2.1;
    @Builder.Default
    String name = "REM_SLEEP_FILTER";;
    @Builder.Default
    String link = "https://neurologia.com/noticia/6398/menor-sueno-rem-se-traduciria-en-un-riesgo-mas-alto-de-demencia";
    @Builder.Default
    String summary = "Menor sueño REM se traduciría en un riesgo más alto de demencia";
}
