package com.oscarmartinez.codechanllenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscarmartinez.codechanllenge.model.CallLog;
import com.oscarmartinez.codechanllenge.repository.CallLogRepository;

@RestController
@RequestMapping("/api/history")
public class CallLogController {

    private final CallLogRepository repository;

    public CallLogController(CallLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CallLog> getAllLogs() {
        return repository.findAll();
    }

}
