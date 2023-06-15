package com.alvarodelaflor.analyzer.services;

import com.alvarodelaflor.analyzer.domain.signals.Signal;
import com.alvarodelaflor.analyzer.repository.SignalDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DeviceService {

    @Autowired
    SignalDao signalDao;


    public List<Signal> getAllSignalRecordsRedis() {
        return signalDao.findAll();
    }
}
