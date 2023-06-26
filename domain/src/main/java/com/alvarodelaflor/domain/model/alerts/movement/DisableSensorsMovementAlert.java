package com.alvarodelaflor.domain.model.alerts.movement;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    String link = "https://repositori.uji.es/xmlui/handle/10234/167159";
    @Builder.Default
    String summary = "Las personas que perciben su deterioro cognitivo pone en marcha mecanismos de defensa para ocultar sus fallos ante los demás";
    @Builder.Default
    String descriptionName = "Desactivación de sensores";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
    String customText = "";

    public String getCustomText() {
        return "En concreto, este usuario se ha despertado un total de " + getNumberOfTimes() + " veces, siendo la hora exacta de esos despertares las siguientes: " + formatDates(dateTimeList.values()) + ".";
    }

    public String formatDates(Collection<List<LocalDateTime>> collectionDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return collectionDates.stream().flatMap(dates -> dates.stream().map(date -> date.format(formatter))).collect(Collectors.toList()).toString();
    }
}
