package com.stock.ex.controller;

import com.stock.ex.model.Stock;
import com.stock.ex.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService service;

    @GetMapping(produces=MediaType.TEXT_EVENT_STREAM_VALUE )
    public Flux<Stock> live(){
        return service.stream();
    }

    @PostMapping(value = "/close/{password}")
    public Mono<Void> stop(){
        service.complete();
        return Mono.empty();
    }
}
