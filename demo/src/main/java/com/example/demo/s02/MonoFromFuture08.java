package com.example.demo.s02;

import com.example.demo.comman.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture08 {
    public static final Logger log = LoggerFactory.getLogger(MonoFromSuppler05.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(()->getName())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()->{
            log.info("generating name");
            return switch ((int)(Math.random()*10)){
                case 1->"sahil";
                case 2->"golu";
                case 3->"sumit";
                case 4->"harsh";
                case 5->"aalekhh";
                case 6->"amit";
                default->"kamal";
            };
        });
    }


}
