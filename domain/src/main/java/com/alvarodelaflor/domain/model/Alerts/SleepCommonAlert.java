package com.alvarodelaflor.domain.model.Alerts;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SleepCommonAlert {

    public String link;
    public String summary;
    public String name;
}
