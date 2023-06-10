package com.alvarodelaflor.sensors.web;

import com.alvarodelaflor.sensors.connector.TuyaConnector;
import com.alvarodelaflor.sensors.domain.DeviceLog;
import com.alvarodelaflor.sensors.domain.DeviceLogContainer;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private Gson gson = new Gson();

    @GetMapping("/logs")
    public Object tokenAvailable() throws IOException {
        String accessToken = TuyaConnector.getToken().getResult().getAccessToken();
        String deviceId = "9b301cc2337d6386459k3b";
        String endTime = "1686509799000";
        String startTime = "1686336999000";
        String pathParam = "?end_time=" + endTime + "&event_types=7&start_time=" + startTime;
        Response response =  TuyaConnector.execute(accessToken, "/v1.0/iot-03/devices/" + deviceId + "/logs" + pathParam, "GET", "", new HashMap<>());
        return gson.fromJson(response.body().string(), DeviceLogContainer.class);
    }
}