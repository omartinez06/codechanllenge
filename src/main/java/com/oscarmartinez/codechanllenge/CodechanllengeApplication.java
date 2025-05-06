package com.oscarmartinez.codechanllenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableAsync
public class CodechanllengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodechanllengeApplication.class, args);
	}

}
