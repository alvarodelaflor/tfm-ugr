package com.alvarodelaflor.sensors.repository;

import com.alvarodelaflor.sensors.domain.signals.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SignalDao {

    public static final String HASH_KEY = "Signal";

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Signal save(Signal signal) {
        template.opsForHash().put(HASH_KEY, signal.getId(), signal);
        return signal;
    }

    public List<Signal> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Signal findSignalById(String id) {
        return (Signal) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteSignal(String id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "";
    }
}
