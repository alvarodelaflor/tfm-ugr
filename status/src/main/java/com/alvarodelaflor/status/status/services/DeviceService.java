package com.alvarodelaflor.status.status.services;

import com.alvarodelaflor.status.status.ability.model.Device;
import com.alvarodelaflor.status.status.controller.DeviceConnector;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
@AllArgsConstructor
public class DeviceService {

    @Autowired
    DeviceConnector deviceConnector;

    public Device getById(String deviceId) {
        return deviceConnector.getById(deviceId);
    }

    public Boolean command(String deviceId, List<Map<String, Object>> commands) {
        return deviceConnector.command(deviceId, Map.of("commands", commands));
    }

}