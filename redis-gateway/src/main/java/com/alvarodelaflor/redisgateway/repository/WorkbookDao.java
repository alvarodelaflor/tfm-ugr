package com.alvarodelaflor.redisgateway.repository;

import com.alvarodelaflor.domain.model.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkbookDao {

    public static final String HASH_KEY_WORKBOOK = "WORKBOOK|";

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Workbook save(Workbook workbook, String username) {
        template.opsForHash().put(HASH_KEY_WORKBOOK + username , workbook.getId(), workbook);
        return workbook;
    }

    public Object findAll(String username) {
        return template.opsForHash().values(getSignalHash(username));
    }

    public Workbook findSignalById(String id, String username) {
        return (Workbook) template.opsForHash().get(getSignalHash(username), id);
    }

    public String deleteSignal(String id, String username) {
        template.opsForHash().delete(getSignalHash(username), id);
        return "";
    }

    private String getSignalHash(String username) {
        return HASH_KEY_WORKBOOK + username;
    }
}
