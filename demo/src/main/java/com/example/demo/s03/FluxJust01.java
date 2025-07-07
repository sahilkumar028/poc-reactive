package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class FluxJust01 {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(1,2,3,4,"sahil")
                .delayElements(Duration.ofMillis(300))
                .subscribe(Util.subscriber());
    }
}
