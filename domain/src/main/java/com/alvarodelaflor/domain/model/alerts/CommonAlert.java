package com.alvarodelaflor.domain.model.alerts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonAlert {

    public String link;
    public String summary;
    public String name;
    @Builder.Default
    public List<AlertType> alertType = Arrays.asList(AlertType.INFORM);
}
