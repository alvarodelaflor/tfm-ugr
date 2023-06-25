package com.alvarodelaflor.domain.model.alerts;

import com.alvarodelaflor.domain.model.alerts.movement.DisableSensorsMovementAlert;
import com.alvarodelaflor.domain.model.alerts.movement.RepeatedMovementAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.AwakeningsAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.DayTimeAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.RemAlert;
import com.alvarodelaflor.domain.model.alerts.sleep.WakeUpEarlyAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.BradycardiaAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.HighBloodPressureAlert;
import com.alvarodelaflor.domain.model.alerts.vitalSign.LowBloodPressureAlert;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "name")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AwakeningsAlert.class, name = "AWAKENINGS_SLEEP_FILTER"),
        @JsonSubTypes.Type(value = BradycardiaAlert.class, name = "BRADYCARDIA_VITAL_SIGN_FILTER"),
        @JsonSubTypes.Type(value = DayTimeAlert.class, name = "DAYTIME_NAP_SLEEP_FILTER"),
        @JsonSubTypes.Type(value = DisableSensorsMovementAlert.class, name = "DISABLE_SENSORS_MOVEMENT_FILTER"),
        @JsonSubTypes.Type(value = HighBloodPressureAlert.class, name = "HIGH_BLOOD_PRESSURE_VITAL_SIGN_FILTER"),
        @JsonSubTypes.Type(value = LowBloodPressureAlert.class, name = "LOW_BLOOD_PRESSURE_VITAL_SIGN_FILTER"),
        @JsonSubTypes.Type(value = RemAlert.class, name = "REM_SLEEP_FILTER"),
        @JsonSubTypes.Type(value = RepeatedMovementAlert.class, name = "REPEAT_MOVEMENT_FILTER"),
        @JsonSubTypes.Type(value = WakeUpEarlyAlert.class, name = "WAKE_UP_EARLY_SLEEP_FILTER")
})
public abstract class CommonAlert implements Serializable{

    private Double weight;
    private String link;
    private String summary;
    private CommonAlertName name;
    private String descriptionName;
    private List<AlertType> alertType;
    private String customText;

    public abstract void setWeight(Double weight);
    public abstract void setSummary(String summary);
    public abstract void setName(CommonAlertName name);
    public abstract void setDescriptionName(String descriptionName);
    public abstract void setAlertType(List<AlertType> alertType);
    public abstract String getCustomText();

    public enum CommonAlertName implements Serializable {
        BRADYCARDIA_VITAL_SIGN_FILTER,
        DISABLE_SENSORS_MOVEMENT_FILTER,
        REPEAT_MOVEMENT_FILTER,
        AWAKENINGS_SLEEP_FILTER,
        DAYTIME_NAP_SLEEP_FILTER,
        REM_SLEEP_FILTER,
        WAKE_UP_EARLY_SLEEP_FILTER,
        HIGH_BLOOD_PRESSURE_VITAL_SIGN_FILTER,
        LOW_BLOOD_PRESSURE_VITAL_SIGN_FILTER;
    }
}
