package com.example.demo.s04;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class TakeOperator05 {
    public static void main(String[] args) {
        takeUntil();
    }

    public static void take(){
        Flux.range(1,10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }
    public static void takeWhile(){
        Flux.range(1,10)
                .log("take")
                .takeWhile(i->i<5)
                .log("sub")
                .subscribe(Util.subscriber());
    }
    public static void takeUntil(){
        Flux.range(1,10)
                .log("take")
                .takeUntil(i->i<5)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
