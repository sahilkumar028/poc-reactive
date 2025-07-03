package com.slowapi.slow.controller;

import com.slowapi.slow.model.UsersLog;
import com.slowapi.slow.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/check")
//@RequiredArgsConstructor
public class UsersLogController {

    @Autowired
    private UserLogService userLogService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UsersLog> check(){
        return userLogService.getAllUserLogs();
    }

    @GetMapping(value="/name",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> checkName(){
        return userLogService.getAllName();
    }
}
