package com.oscarmartinez.codechanllenge.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.oscarmartinez.codechanllenge.model.CallLog;
import com.oscarmartinez.codechanllenge.repository.CallLogRepository;

import jakarta.transaction.Transactional;

@Service
public class CallLogService {
	
	@Autowired
	CallLogRepository repository;

    public CallLogService(CallLogRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional
    public void logCall(String endpoint, String params, String response, boolean isError) {
        CallLog log = CallLog.builder()
                .timestamp(LocalDateTime.now())
                .endpoint(endpoint)
                .parameters(params)
                .response(response)
                .isError(isError)
                .build();

        repository.save(log);
    }

}
