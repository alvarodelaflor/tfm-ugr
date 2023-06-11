package com.alvarodelaflor.sensors.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samsung/devices")
public class SamsungDeviceController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}