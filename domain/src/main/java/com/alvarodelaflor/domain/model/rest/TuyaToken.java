package com.alvarodelaflor.domain.model.rest;

import com.alvarodelaflor.domain.model.rest.common.CommonContainer;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TuyaToken extends CommonContainer {

    public Token result;

    @Getter
    public class Token {
        @SerializedName("access_token")
        String accessToken;
        @SerializedName("expire_time")
        Long expireTime;
        @SerializedName("refresh_token")
        String refreshToken;
        String uid;
    }
}
