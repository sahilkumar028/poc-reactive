package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class FLuxEmptyError10 {
    public static void main(String[] args) throws InterruptedException {
        Flux.empty()
                .subscribe(Util.subscriber());
        Flux.error(
                new RuntimeException("its exit")
        ).subscribe(Util.subscriber());
        Util.sleepSeconds(4);
    }
}
