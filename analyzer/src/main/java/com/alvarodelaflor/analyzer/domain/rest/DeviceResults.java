package com.alvarodelaflor.analyzer.domain.rest;

import com.alvarodelaflor.analyzer.domain.common.CommonContainer;
import com.alvarodelaflor.analyzer.domain.common.CommonResult;
import lombok.Getter;

import java.util.List;


@Getter
public class DeviceResults extends CommonContainer {
    public DevicesContainer result;

    @Getter
    public class DevicesContainer extends CommonResult {
        public List<DeviceItem> list;
        public Double total;
    }

    @Getter
    public class DeviceItem {
        private String active_time;
        private String asset_id;
        private String category;
        private String category_name;
        private String create_time;
        private String gateway_id;
        private String icon;
        private String id;
        private String ip;
        private String lat;
        private String local_key;
        private String lon;
        private String model;
        private String name;
        private Boolean online;
        private String product_id;
        private String product_name;
        private Boolean sub;
        private String time_zone;
        private String update_time;
        private String uuid;
    }
}
