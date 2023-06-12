package com.alvarodelaflor.sensors.domain.signals;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@RedisHash("Signal")
public class Signal implements Serializable {
    @Id
    private String id;
    private SamsungWearSignal samsungWearSignals;
    private List<TuyaPirSignal> tuyaPirSignals;
}
