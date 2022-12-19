package com.example.bestpractices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportAutoConfiguration
@EnableScheduling
@EnableFeignClients
public class BestPracticesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestPracticesApplication.class, args);
	}

}
