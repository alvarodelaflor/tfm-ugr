package com.alvarodelaflor.domain.model.alerts.sleep;

import com.alvarodelaflor.domain.model.alerts.AlertType;
import com.alvarodelaflor.domain.model.alerts.CommonAlert;
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
public class RemAlert extends CommonAlert implements Serializable {

    Long duration;
    @Builder.Default
    Double weight = 2.1;
    @Builder.Default
    String link = "https://neurologia.com/noticia/6398/menor-sueno-rem-se-traduciria-en-un-riesgo-mas-alto-de-demencia";
    @Builder.Default
    String summary = "Menor sueño REM se traduciría en un riesgo más alto de demencia";
    @Builder.Default
    String descriptionName = "Poca cantidad de sueño REM";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);

    String customText = "";

    public String getCustomText() {
        return "Pare el usuario del sistema, se han contabilizado tan solo " + duration + " minutos de sueño REM.";
    }
}
