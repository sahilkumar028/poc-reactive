package com.example.demo.s04;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class FluuxGenerate06 {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);// this will emit  one element at a time
//            synchronousSink.complete(); // if not complete then it will infinite
            })
                .take(4)
                .subscribe(Util.subscriber());

    }
}
