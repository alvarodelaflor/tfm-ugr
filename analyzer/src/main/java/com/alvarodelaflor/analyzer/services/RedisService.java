package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.domain.signals.Signal;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RedisService {

    private static String REDIS_URL = "http://redis-gateway:8082/redis/";
    private static String SIGNAL_ENDPOINT = "signals/";

    private static String SIGNAL_URL = REDIS_URL + SIGNAL_ENDPOINT;
    private static RestTemplate REST_TEMPLATE = new RestTemplate();

    public List<Signal> findAllSignalsByUsername(String username) {
        return REST_TEMPLATE.exchange(SIGNAL_URL + "{username}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Signal>>() {}, username).getBody();
    }

    public void deleteSignal(String id, String username) {
    }
}
