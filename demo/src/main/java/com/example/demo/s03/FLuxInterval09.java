package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FLuxInterval09 {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(500))
                .map(i->(char)(Math.random()*100))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
