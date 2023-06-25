package com.alvarodelaflor.redisgateway.web;

import com.alvarodelaflor.domain.model.Workbook;
import com.alvarodelaflor.domain.model.signals.Signal;
import com.alvarodelaflor.redisgateway.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @PostMapping("/signals/{username}")
    public void saveSignal(
            @RequestBody Signal signal,
            @PathVariable("username") String username
    ) {
        this.redisService.saveSignal(signal, username);
    }

    @GetMapping("/signals/{username}")
    public List<Signal> getAllSignalsByUsername(
            @PathVariable(value = "username") String username
    ) {
        return this.redisService.getAllSignalsByUser(username);
    }

    @DeleteMapping("/signals/{username}/{id}")
    public void deleteSignalByUsernameAndId(
            @PathVariable(value = "id") String id,
            @PathVariable(value = "username") String username
    ) {
        this.redisService.deleteSignal(id, username);
    }

    @PostMapping("/workbooks/{username}")
    public void saveWorkbook(
            @RequestBody Workbook workbook,
            @PathVariable("username") String username
    ) {
        this.redisService.saveWorkbook(workbook, username);
    }

    @GetMapping("/workbooks/{username}")
    public List<Workbook> getWorkbookByUsername(
            @PathVariable(value = "username") String username
    ) {
        return this.redisService.getWorkbookByUser(username);
    }
}
