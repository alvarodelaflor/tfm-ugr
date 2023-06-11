package com.alvarodelaflor.sensors.web;

import com.alvarodelaflor.sensors.connector.TuyaConnector;
import com.alvarodelaflor.sensors.domain.rest.TuyaToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/token")
    public TuyaToken.Token tokenAvailable() throws IOException {
        return TuyaConnector.getToken().getResult();
    }
}