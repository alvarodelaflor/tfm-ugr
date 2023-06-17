package com.alvarodelaflor.sensors.web;

import com.alvarodelaflor.domain.model.debug.FakeSamsungValue;
import com.alvarodelaflor.domain.model.debug.FakeSignal;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.sensors.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/devices")
public class DeviceController {

   @Autowired
   public DeviceService deviceService;

    @GetMapping("/signal")
    public Signal ping(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "startTime") LocalDateTime startDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = { "dd/MM/yyyy HH:mm" }) @RequestParam(value = "endTime") LocalDateTime endDateTime,
            @RequestParam(required = false, value = "debug") String debug,
            @RequestParam(required = false, value = "fakeSamsungValue") String fakeSamsungValue,
            @RequestParam("username") String usernname
    ) {
        return this.deviceService.getAndSaveAllDeviceSignals(startDateTime, endDateTime, FakeSignal.fromDebugParam(debug), FakeSamsungValue.fromValue(fakeSamsungValue), usernname);
    }
}