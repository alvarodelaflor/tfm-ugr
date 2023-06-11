package com.alvarodelaflor.sensors.domain.rest;

import com.alvarodelaflor.sensors.domain.rest.common.CommonContainer;
import com.alvarodelaflor.sensors.domain.rest.common.CommonResult;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
public class DeviceLogs extends CommonContainer {

    public Log result;

    @Getter
    public class Log extends CommonResult {
        @SerializedName("device_id")
        String deviceId;
        @SerializedName("has_more")
        Boolean hasMore;
        @SerializedName("last_row_key")
        String lastRowKey;
        @SerializedName("list")
        List<Event> eventList;
    }

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
}
