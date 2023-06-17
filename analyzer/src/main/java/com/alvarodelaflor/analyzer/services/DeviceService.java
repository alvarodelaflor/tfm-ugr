package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.domain.signals.Signal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeviceService {

    @Autowired
    RedisService redisService;

    public List<Signal> getAllSignalRecordsRedis(String username) {
        return redisService.findAllSignalsByUsername(username);
    }
}
