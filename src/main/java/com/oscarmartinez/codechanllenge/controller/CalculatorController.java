package com.oscarmartinez.codechanllenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oscarmartinez.codechanllenge.model.ResponseResult;
import com.oscarmartinez.codechanllenge.service.PercentageService;

/**
 * Controller responsible for handling requests related to calculations.
 * It receives two numbers, adds them, and applies a percentage value retrieved from a caching service.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CalculatorController {
	
	@Autowired
	private PercentageService percentageService;

	/**
     * Endpoint to calculate the sum of two numbers and apply a percentage to it.
     * 
     * <p>The percentage value is retrieved from the external service and is cached for 30 minutes.</p>
     * <p>If the external service fails, the cached value is used. If no cached value is available,
     * an error will be thrown.</p>
     * 
     * @param num1 The first number to be added.
     * @param num2 The second number to be added.
     * @return A ResponseResult object containing the sum of the numbers, the percentage, and the final result.
     * @throws RuntimeException If an error occurs while retrieving the percentage from the service or cache.
     */
    @GetMapping("/calculate")
    public ResponseResult calcular(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
    	int add = num1 + num2;

        double percentage;
        
        try {
            // Attempt to retrieve the percentage from the external service
            percentage = percentageService.getPercentage();
        } catch (Exception e) {
            // If the service fails, use the cached percentage value
            percentage = percentageService.getPercentage(); // Retrieved from cache
        }

        // Calculating the final result
        double finalResult = add + (add * (percentage / 100));

        // Returning the result in a ResponseResult object
        return ResponseResult.builder()
                             .add(add)
                             .percentage(percentage)
                             .finalResult(finalResult)
                             .build();
    }
    
    @GetMapping("/cached-percentage")
    public Double getCachedPercentage() {
        return percentageService.getCachedPercentage();
    }
}
