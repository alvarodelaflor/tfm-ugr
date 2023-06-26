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
import java.util.*;
import java.util.stream.Collectors;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepeatedMovementAlert extends CommonAlert implements Serializable {

    Map<String, List<LocalDateTime>> repeatedMovement;
    Integer repeatedMovementNumber;
    @Builder.Default
    Double weight = 2.1;
    @Builder.Default
    String link = "https://www.alz.org/alzheimer-demencia/las-10-senales#:~:text=Desorientaci%C3%B3n%20de%20tiempo%20o%20lugar,est%C3%A1n%20y%20c%C3%B3mo%20llegaron%20all%C3%AD.";
    @Builder.Default
    String summary = "Es posible que a las personas con deterioros cognitivos se les olvide a veces dónde están y cómo llegaron allí";
    @Builder.Default
    String descriptionName = "Movimientos repetidos";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
    String customText = "";

    public String getCustomText() {
        return "En este caso, el usuario ha realizado un número total de " + repeatedMovementNumber + ". A continuación se muestra un listado, en el que por cada uno de los sensores se detalla la hora a la que se considera que el movimiento es repetido: " + formatDates(repeatedMovement) + ".";
    }

    public static Map<String, List<String>> formatDates(Map<String, List<LocalDateTime>> repeatedMovement) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return repeatedMovement.entrySet().stream()
                .collect(
                        HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), formatDateTimeList(entry.getValue(), formatter)),
                        HashMap::putAll
                );
    }

    public static List<String> formatDateTimeList(List<LocalDateTime> dateTimeList, DateTimeFormatter formatter) {
        return dateTimeList.stream()
                .map(dateTime -> dateTime.format(formatter))
                .toList();
    }
}
