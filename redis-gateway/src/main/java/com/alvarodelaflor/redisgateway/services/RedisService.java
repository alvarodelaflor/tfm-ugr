package com.alvarodelaflor.redisgateway.services;

import com.alvarodelaflor.redisgateway.domain.signals.Signal;
import com.alvarodelaflor.redisgateway.repository.SignalDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    SignalDao signalDao;
    @Autowired
    ObjectMapper mapper;

    public void saveSignal(Signal signal, String username) {
        this.signalDao.save(signal, username);
    }

    public List<Signal> getAllSignalsByUser(String username) {
        List<Signal> res = mapper.convertValue(signalDao.findAll(username), new TypeReference<List<Signal>>() { });

        return res;
    }
}
