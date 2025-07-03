package com.saha.institutemgt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleRuntime(RuntimeException e){
        Map<String,Object> response =new HashMap<>();
        response.put("error",true);
        response.put("message",e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return Mono.just(ResponseEntity.badRequest().body(response));
    }
}
