package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class MultipleSubscribers02 {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4,5,6);
        flux.subscribe(Util.subscriber("sub1"));
        flux.filter(i->i>7)
                .subscribe(Util.subscriber("sub2"));
        flux.filter(i->i%2==0)
                .map(i->i+"a")
                .subscribe(Util.subscriber("sub3"));
    }
}
