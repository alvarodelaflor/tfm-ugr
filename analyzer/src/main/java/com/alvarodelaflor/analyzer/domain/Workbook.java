package com.alvarodelaflor.analyzer.domain;

import com.alvarodelaflor.analyzer.domain.signals.Signal;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@RedisHash("Workbook")
public class Workbook implements Serializable {

    private Signal signal;
}
