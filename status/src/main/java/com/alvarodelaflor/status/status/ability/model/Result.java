package com.alvarodelaflor.status.status.ability.model;

import lombok.Value;

import java.util.Date;

@Value
public class Result {
    public String access_token;
    public Date expiretime;
    public String refresh_token;
    public String uid;
}
