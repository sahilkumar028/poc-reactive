package com.example.EnternalApiCall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EnternalApiCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnternalApiCallApplication.class, args);
	}

}
