package com.alvarodelaflor.sensors.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class DeviceLogContainer {

    DeviceLog result;
    Boolean success;
    String t;
    String tid;
}
