package com.oscarmartinez.codechanllenge.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "percentage-service", url = "http://localhost:8081")
public interface PercentageClient {
	
	@GetMapping("/mock/percentage")
    double getPercentage();

}
