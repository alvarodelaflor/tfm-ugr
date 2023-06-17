package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;
import com.alvarodelaflor.sensors.web.TuyaDeviceController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TuyaDeviceService {

    @Autowired
    TuyaDeviceController tuyaDeviceController;

    public List<TuyaPirSignal> getPirSignals(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return this.tuyaDeviceController.getPirSignal(startDateTime, endDateTime);
    }
}
