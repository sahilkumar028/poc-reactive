package com.slowapi.slow;

import com.slowapi.slow.model.UsersLog;
import com.slowapi.slow.service.UserLogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlowApplication.class, args);
	}


}
