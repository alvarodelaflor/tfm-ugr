package com.alvarodelaflor.sensors.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class DeviceLog {

    @SerializedName("device_id")
    String deviceId;
    @SerializedName("has_more")
    Boolean hasMore;
    @SerializedName("last_row_key")
    String lastRowKey;
    @SerializedName("list")
    List<Event> eventList;
}
