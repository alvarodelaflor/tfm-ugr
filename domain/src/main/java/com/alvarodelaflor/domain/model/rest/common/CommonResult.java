package com.alvarodelaflor.domain.model.rest.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResult {
    @JsonProperty("has_more")
    public Boolean hasMore;
    @JsonProperty("last_row_key")
    public String lastRowKey;
}
