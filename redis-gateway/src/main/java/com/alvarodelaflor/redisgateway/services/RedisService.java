package com.alvarodelaflor.redisgateway.services;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.redisgateway.repository.SignalDao;
import com.alvarodelaflor.redisgateway.repository.WorkbookDao;
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
    WorkbookDao workbookDao;
    @Autowired
    ObjectMapper mapper;

    public void saveSignal(Signal signal, String username) {
        this.signalDao.save(signal, username);
    }

    public List<Signal> getAllSignalsByUser(String username) {
        List<Signal> res = mapper.convertValue(signalDao.findAll(username), new TypeReference<List<Signal>>() { });

        return res;
    }

    public void deleteSignal(String id, String username) {
        signalDao.deleteSignal(id, username);
    }

    public void saveWorkbook(Workbook workbook, String username) {
        this.workbookDao.save(workbook, username);
    }

    public List<Workbook> getWorkbooksByUser(String username) {
        List<Workbook> res = mapper.convertValue(workbookDao.findAll(username), new TypeReference<List<Workbook>>() { });

        return res;
    }

    public void deleteWorkbook(String id, String username) {
        workbookDao.deleteWorkbook(id, username);
    }
}
