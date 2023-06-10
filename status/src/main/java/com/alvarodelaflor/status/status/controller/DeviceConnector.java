package com.alvarodelaflor.status.status.controller;

import com.alvarodelaflor.status.status.ability.model.Device;
import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;

import java.util.Map;

public interface DeviceConnector {
    /**
     * query device info by device_id
     * @param deviceId
     * @return
     */
    @GET("/v1.1/iot-03/devices/{device_id}")
    Device getById(@Path("device_id") String deviceId);

    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean command(@Path("device_id") String deviceId, @Body Map<String, Object> commands);
}