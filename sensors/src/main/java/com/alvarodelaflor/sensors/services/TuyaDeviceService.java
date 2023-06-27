package com.alvarodelaflor.sensors.services;

import com.alvarodelaflor.domain.model.rest.DeviceLogs;
import com.alvarodelaflor.domain.model.rest.DeviceResults;
import com.alvarodelaflor.domain.model.signals.TuyaPirSignal;
import com.alvarodelaflor.sensors.connector.TuyaConnector;
import com.alvarodelaflor.sensors.web.TuyaDeviceController;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TuyaDeviceService {

    @Autowired
    TuyaDeviceController tuyaDeviceController;
    private static Gson gson = new Gson();

    public DeviceResults getDevices() {
        String accessToken = TuyaConnector.getToken().getResult().getAccessToken();

        String response =  TuyaConnector.execute(accessToken, TuyaConnector.baseApiVersion3 + "/devices", "GET", "", new HashMap<>());

        return gson.fromJson(response, DeviceResults.class);
    }

    public DeviceLogs getLogsByDeviceId(String deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        String accessToken = TuyaConnector.getToken().getResult().getAccessToken();
        Long endTimeEpoch = endTime.toEpochSecond(OffsetDateTime.now().getOffset());
        Long startTimeEpoch = startTime.toEpochSecond(OffsetDateTime.now().getOffset());

        String path = TuyaConnector.baseApiVersion + String.format("/devices/%s/logs?end_time=%s&event_types=7&start_time=%s&size=100", deviceId, endTimeEpoch, startTimeEpoch);
        String response =  TuyaConnector.execute(accessToken, path, "GET", "", new HashMap<>());

        DeviceLogs deviceLogs =  gson.fromJson(response, DeviceLogs.class);
        Set<DeviceLogs.Event> events = deviceLogs.getResult().getEventList().stream().collect(Collectors.toSet());
        while (deviceLogs.getResult().getHasMore()) {
            String pathHasMore = TuyaConnector.baseApiVersion + String.format("/devices/%s/logs?end_time=%s&event_types=7&start_time=%s&size=100&last_row_key=%s", deviceId, endTimeEpoch, startTimeEpoch, deviceLogs.getResult().getLastRowKey());
            String responseHasMore =  TuyaConnector.execute(accessToken, pathHasMore, "GET", "", new HashMap<>());
            deviceLogs = gson.fromJson(responseHasMore, DeviceLogs.class);
            events.addAll(deviceLogs.getResult().getEventList().stream().collect(Collectors.toSet()));
        }
        deviceLogs.getResult().setEventList(events.stream().sorted((Comparator.comparing(DeviceLogs.Event::getTime).reversed())).toList());
        return deviceLogs;
    }

    public List<DeviceLogs> getLogs(LocalDateTime startTime, LocalDateTime endTime) {
        return getDevices().getResult().getList().stream()
                .filter(x-> x.getIp() != null)
                .map(x -> getLogsByDeviceId(x.getId(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public List<TuyaPirSignal> getPirSignals(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return getLogs(startDateTime, endDateTime).stream()
                .flatMap(deviceLogs -> deviceLogs.getResult().getEventList().stream()
                        .filter(event -> event.getValue().matches("^(pir|true|false|30)$"))
                        .map(event -> new TuyaPirSignal(deviceLogs.getResult().getDeviceId(), event.getTime(), event.getValue().equals("pir") ? event.getTime().plusSeconds(33): null, TuyaPirSignal.getStatus(event.getValue()))))
                .collect(Collectors.toList());
    }
}
