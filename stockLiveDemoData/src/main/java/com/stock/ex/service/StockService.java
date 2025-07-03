package com.stock.ex.service;

import com.stock.ex.model.Stock;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class StockService {
    private final Sinks.Many<Stock> sink=Sinks.many().multicast().onBackpressureBuffer();


    public void update(Stock price){
        sink.tryEmitNext(price);
    }
    public void     complete(){
        sink.tryEmitComplete();
    }
    public Flux<Stock> stream(){
        return sink.asFlux();
    }
    @PostConstruct
    public void start() {

        Flux.interval(Duration.ofSeconds(1))
                .map(e->new Stock("Tata",(100+Math.random()*10), LocalDateTime.now()))
                .subscribe(e->update(e));
    }
}
