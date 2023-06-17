package com.alvarodelaflor.redisgateway.web;

import com.alvarodelaflor.redisgateway.domain.signals.Signal;
import com.alvarodelaflor.redisgateway.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @PostMapping("/signals/{username}")
    public void saveSignal(
            @RequestBody Signal signal,
            @PathVariable("username") String username
    ) {
        this.redisService.saveSignal(signal, username);
    }

    @GetMapping("/signals/{username}")
    public List<Signal> getAllSignalsByUsername(
            @PathVariable(value = "username") String username
    ) {
        return this.redisService.getAllSignalsByUser(username);
    }
}
