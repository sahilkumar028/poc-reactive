package com.slowapi.slow.service;

import com.slowapi.slow.model.UsersLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserLogService {
    private  final WebClient webClient;

//    public UserLogService(){
//
//    }
    public UserLogService (WebClient.Builder builder){
        this.webClient = builder
                .baseUrl("http://localhost:1234")
                .build();
    }
    public Flux<UsersLog> getAllUserLogs(){
        return webClient.get()
                .uri("/api/allUsers")
                .retrieve()
                .bodyToFlux(UsersLog.class);
    }
    public Flux<String> getAllName(){
        return webClient.get()
                .uri("/api/getName")
                .retrieve()
                .bodyToFlux(String.class)
                .limitRate(10)
                .delayElements(Duration.ofSeconds(1));
    }
    public void run(){
        getAllName().subscribe(e-> System.out.println(e));
    }
}
