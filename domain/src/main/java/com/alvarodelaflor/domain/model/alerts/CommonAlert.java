package com.alvarodelaflor.domain.model.alerts;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class CommonAlert {

    private Double weight;
    private String link;
    private String summary;
    private CommonAlertName name;
    private String descriptionName;
    private List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
    private String customText;

    public abstract void setWeight(Double weight);
    public abstract void setSummary(String summary);
    public abstract void setName(CommonAlertName name);
    public abstract void setDescriptionName(String descriptionName);
    public abstract void setAlertType(List<AlertType> alertType);
    public abstract String getCustomText();

    public enum CommonAlertName implements Serializable {
        BRADYCARDIA_VITAL_SIGN_FILTER, DISABLE_SENSORS_MOVEMENT_FILTER, REPEAT_MOVEMENT_FILTER, AWAKENINGS_SLEEP_FILTER, DAYTIME_NAP_SLEEP_FILTER, REM_SLEEP_FILTER, WAKE_UP_EARLY_SLEEP_FILTER, HIGH_BLOOD_PRESSURE_VITAL_SIGN_FILTER, LOW_BLOOD_PRESSURE_VITAL_SIGN_FILTER;
    }
}
