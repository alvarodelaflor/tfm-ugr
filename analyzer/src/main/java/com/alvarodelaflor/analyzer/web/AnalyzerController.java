package com.alvarodelaflor.analyzer.web;

import com.alvarodelaflor.analyzer.services.AnalyzeService;
import com.alvarodelaflor.analyzer.services.RedisService;
import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.signals.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyzer")
public class AnalyzerController {

    @Autowired
    AnalyzeService analyzeService;
    @Autowired
    RedisService redisService;

    @GetMapping("/signals/{username}")
    public List<Workbook> analizeSignal(
            @PathVariable(value = "username") String username
    ) {
        List<Signal> signals = this.redisService.findAllSignalsByUsername(username);
        return analyzeService.analyzeSignals(signals, username);
    }
}