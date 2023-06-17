package com.alvarodelaflor.domain.model;

import com.alvarodelaflor.domain.model.signals.Signal;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Map;

@Getter
@Builder
@RedisHash("Workbook")
public class Workbook implements Serializable {

    private SleepAlert sleepAlert;

    @Getter
    @Builder
    public static class SleepAlert implements Serializable {
        Map<String, Double> reemSleep;
    }
}
