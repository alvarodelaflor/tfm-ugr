package com.alvarodelaflor.sensors.web;

import com.alvarodelaflor.sensors.connector.TuyaConnector;
import com.alvarodelaflor.sensors.domain.TuyaToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping("/token")
    public TuyaToken tokenAvailable() throws IOException {
        return TuyaConnector.getToken().getResult();
    }
}