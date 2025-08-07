package com.example.demo.s04;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class GenerateWithState08 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            String name= Util.faker().country().name();
            synchronousSink.next(name);
            atomicInteger.incrementAndGet();
            if(atomicInteger.get()==10||name.equalsIgnoreCase("canada")){
                synchronousSink.complete();
            }
        })
        .subscribe(Util.subscriber());
    }
}
