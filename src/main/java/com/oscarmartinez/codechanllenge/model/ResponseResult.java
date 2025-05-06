package com.oscarmartinez.codechanllenge.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseResult {
	
	private int add;
    private double percentage;
    private double finalResult;

}
