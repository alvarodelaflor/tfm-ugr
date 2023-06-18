package com.alvarodelaflor.domain.model;

import com.alvarodelaflor.domain.model.alerts.CommonAlert;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@RedisHash("Workbook")
public class Workbook implements Serializable {

    private String id;
    private List<CommonAlert> commonAlerts;
}
