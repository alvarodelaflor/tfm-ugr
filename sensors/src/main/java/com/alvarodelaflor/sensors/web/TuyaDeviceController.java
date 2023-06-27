package com.alvarodelaflor.sensors.web;

import com.alvarodelaflor.domain.model.rest.DeviceLogs;
import com.alvarodelaflor.domain.model.rest.DeviceResults;
import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;
import com.alvarodelaflor.sensors.services.TuyaDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tuya/devices")
public class TuyaDeviceController {

    @Autowired
    TuyaDeviceService tuyaDeviceService;

    @GetMapping()
    public DeviceResults getDevices() {
        return tuyaDeviceService.getDevices();
    }

    @GetMapping("/logs/{deviceId}")
    public DeviceLogs getLogsByDeviceId(
            @PathVariable("deviceId") String deviceId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "startTime") LocalDateTime startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "endTime") LocalDateTime endTime
        ) {
        return tuyaDeviceService.getLogsByDeviceId(deviceId, startTime, endTime);
    }

    @GetMapping("/logs")
    public List<DeviceLogs> getLogs(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "startTime") LocalDateTime startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "endTime") LocalDateTime endTime
    ) {
        return tuyaDeviceService.getLogs(startTime, endTime);
    }


    @GetMapping("/pirSignal")
    public List<TuyaPirSignal> getPirSignal(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "startTime") LocalDateTime startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "endTime") LocalDateTime endTime
    ) {
        return tuyaDeviceService.getPirSignals(startTime, endTime);
    }
}