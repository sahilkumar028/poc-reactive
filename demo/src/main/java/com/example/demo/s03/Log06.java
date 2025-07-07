package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class Log06 {
    public static void main(String[] args) {
        Flux.range(1,5)
                .log()
                .subscribe(Util.subscriber());
    }
}
