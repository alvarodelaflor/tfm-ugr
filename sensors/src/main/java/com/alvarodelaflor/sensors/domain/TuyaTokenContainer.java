package com.alvarodelaflor.sensors.domain;

import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;

@Data
public class TuyaTokenContainer {

    TuyaToken result;
    Boolean success;
    Double t;
    String tid;

}