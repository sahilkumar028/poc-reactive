package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxFromStream04 {
    public static void main(String[] args) {

        var list= List.of(1,2,3,4,5,6);
        Stream<Integer> stream=list.stream(); // it will consume only once
        Flux<Integer> flux=Flux.fromStream(stream);
        flux.subscribe(Util.subscriber("sahil"));
//        flux.subscribe(Util.subscriber("amit")); // throw exception due to stream is already used

        Flux<Integer> flux1=Flux.fromStream(()->list.stream());
        flux1.subscribe(Util.subscriber("sahil"));
        flux1.subscribe(Util.subscriber("amit"));
    }
}
