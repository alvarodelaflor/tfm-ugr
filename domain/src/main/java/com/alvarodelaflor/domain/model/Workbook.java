package com.alvarodelaflor.domain.model;

import com.alvarodelaflor.domain.model.signals.Signal;
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
