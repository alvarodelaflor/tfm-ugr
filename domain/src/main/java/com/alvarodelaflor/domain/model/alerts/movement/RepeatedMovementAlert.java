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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    CommonAlertName name = CommonAlertName.REPEAT_MOVEMENT_FILTER;
    @Builder.Default
    String link = "https://www.alz.org/alzheimer-demencia/las-10-senales#:~:text=Desorientaci%C3%B3n%20de%20tiempo%20o%20lugar,est%C3%A1n%20y%20c%C3%B3mo%20llegaron%20all%C3%AD.";
    @Builder.Default
    String summary = "Es posible que a las personas con deterioros cognitivos se les olvide a veces dónde están y cómo llegaron allí";
    @Builder.Default
    String descriptionName = "Movimientos repetidos";
    @Builder.Default
    List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
    @Builder.Default
    String customText = "";

}
