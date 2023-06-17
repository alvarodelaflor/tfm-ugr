package com.alvarodelaflor.redisgateway.repository;

import com.alvarodelaflor.redisgateway.domain.signals.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignalDao {

    public static final String HASH_KEY_SIGNAL = "SIGNAL|";

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Signal save(Signal signal, String username) {
        template.opsForHash().put(HASH_KEY_SIGNAL + username , signal.getId(), signal);
        return signal;
    }

    public Object findAll(String username) {
        return template.opsForHash().values(getSignalHash(username));
    }

    public Signal findSignalById(String id, String username) {
        return (Signal) template.opsForHash().get(getSignalHash(username), id);
    }

    public String deleteSignal(String id, String username) {
        template.opsForHash().delete(getSignalHash(username), id);
        return "";
    }

    private String getSignalHash(String username) {
        return HASH_KEY_SIGNAL + username;
    }
}
