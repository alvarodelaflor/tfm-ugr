package com.alvarodelaflor.execution.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RedisService {

    private static String REDIS_URL = "http://redis-gateway:8082/redis/";
    private static String WORKBOOK_ENDPOINT = "workbooks/";

    private static String WORKBOOK_URL = REDIS_URL + WORKBOOK_ENDPOINT;
    private static RestTemplate REST_TEMPLATE = new RestTemplate();

    public List<Workbook> getWorkbookByUsername(String username) {
        return REST_TEMPLATE.exchange(WORKBOOK_URL + "{username}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Workbook>>() {}, username).getBody();
    }

    public void deleteWorkbookByUsernameAndId(String username, String id) {
        REST_TEMPLATE.exchange(WORKBOOK_URL + "{username}/{id}", HttpMethod.DELETE, null, new ParameterizedTypeReference<Workbook>() {}, username, id);
    }
}
