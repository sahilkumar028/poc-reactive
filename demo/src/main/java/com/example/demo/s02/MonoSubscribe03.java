package com.example.demo.s02;

import reactor.core.publisher.Mono;

public class MonoSubscribe03 {

    public static void main(String[] args) {
        Mono<String> mono=Mono.just(1)
                .map(i->i+"hello");
        mono.subscribe(System.out::println
                ,err->System.err.println(err.getMessage())
                ,()->System.out.println("its finish")
                ,subscription -> subscription.request(1));
    }
}
