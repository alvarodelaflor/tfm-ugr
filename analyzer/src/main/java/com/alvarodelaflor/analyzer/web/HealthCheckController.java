package com.alvarodelaflor.analyzer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}