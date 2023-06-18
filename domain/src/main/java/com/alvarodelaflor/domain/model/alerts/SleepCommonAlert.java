package com.alvarodelaflor.domain.model.alerts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SleepCommonAlert {

    public String link;
    public String summary;
    public String name;
    @Builder.Default
    public AlertType alertType = AlertType.INFORM;
}
