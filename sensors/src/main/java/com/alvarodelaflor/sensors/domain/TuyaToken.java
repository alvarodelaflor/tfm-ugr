package com.alvarodelaflor.sensors.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TuyaToken {

    @SerializedName("access_token")
    String accessToken;
    @SerializedName("expire_time")
    Long expireTime;
    @SerializedName("refresh_token")
    String refreshToken;
    String uid;
}
