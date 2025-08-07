package com.example.demo.s04;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class FluxAsssigmenet07 {
    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String name=Util.faker().country().name();
            if(name.equalsIgnoreCase("canada")){
               synchronousSink.complete();
            }
        })
        .subscribe(Util.subscriber());
    }

}
