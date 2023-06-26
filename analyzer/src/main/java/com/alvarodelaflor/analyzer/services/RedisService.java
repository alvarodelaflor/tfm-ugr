package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RedisService {

    private static String REDIS_URL = "http://redis-gateway:8082/redis/";
    private static String SIGNAL_ENDPOINT = "signals/";
    private static String WORKBOOK_ENDPOINT = "workbooks/";

    private static String SIGNAL_URL = REDIS_URL + SIGNAL_ENDPOINT;
    private static String WORKBOOK_URL = REDIS_URL + WORKBOOK_ENDPOINT;
    private static RestTemplate REST_TEMPLATE = new RestTemplate();

    public List<Signal> findAllSignalsByUsername(String username) {
        return REST_TEMPLATE.exchange(SIGNAL_URL + "{username}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Signal>>() {}, username).getBody();
    }

    public Boolean saveWorkbook(Workbook workbook, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder.fromUriString(WORKBOOK_URL + "{username}")
                .buildAndExpand(username)
                .toUriString();

        ResponseEntity<Void> response = REST_TEMPLATE.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(workbook, headers),
                Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteSignal(String id, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder.fromUriString(SIGNAL_URL + String.format("%s/%s", username, id))
                .toUriString();

        ResponseEntity<Void> response = REST_TEMPLATE.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(null, headers),
                Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        } else {
            return false;
        }
    }
}
