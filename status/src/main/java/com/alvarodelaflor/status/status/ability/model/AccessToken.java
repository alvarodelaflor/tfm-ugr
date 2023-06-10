package com.alvarodelaflor.status.status.ability.model;

import lombok.Value;

@Value
public class AccessToken {

    public Result result;
    public Boolean success;
    public Double t;
    public String tid;

    public String getAccessToken() {
        return this.result.getAccess_token();
    }
}
