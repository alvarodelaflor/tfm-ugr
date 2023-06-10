package com.alvarodelaflor.sensors.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Event {

    @SerializedName("event_from")
    Integer from;
    @SerializedName("event_id")
    Integer id;
    @SerializedName("event_time")
    Long time;
    @SerializedName("event_value")
    String value;

    public Integer getFrom() {
        return from;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return Instant.ofEpochMilli(this.time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String getValue() {
        return value;
    }
}
