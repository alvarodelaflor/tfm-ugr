package com.alvarodelaflor.status.status.ability.model;

import lombok.Data;

@Data
public class Alert {

    private Long id;
    private String sensor;
    private String message;
}
