package com.springReactiveLearning.demo.entity;

import reactor.core.publisher.Mono;

import java.io.IOException;

public class E5 {
    public static void main(String[] args) throws IOException {
//        Mono<Long> count=ReactiveSources.intNumberFlux().count();
//        count.subscribe(System.out::println);

//        ReactiveSources.intNumberFlux().collectList()
//                        .subscribe(System.out::println);

        ReactiveSources.intNumberFlux()
                .buffer(2)
                .map(list -> list.get(0)+ list.get(1))
                .subscribe(System.out::println);

        System.in.read();
    }
}
