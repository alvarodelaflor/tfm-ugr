package com.alvarodelaflor.domain.model.alerts.movement;

import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisableSensorsMovementAlert extends CommonAlert implements Serializable {

    Map<String, List<LocalDateTime>> dateTimeList;
    Integer numberOfTimes;
    @Builder.Default
    Double weight = 1.4;
    @Builder.Default
    String name = "DISABLE_SENSORS_MOVEMENT_FILTER";;
    @Builder.Default
    String link = "https://repositori.uji.es/xmlui/handle/10234/167159";
    @Builder.Default
    String summary = "Las personas que perciben su deterioro cognitivo pone en marcha mecanismos de defensa para ocultar sus fallos ante los demás";
}
