package com.example.demo.s02;

import com.example.demo.comman.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoDefer10 {
    private static final Logger log= LoggerFactory
            .getLogger(MonoDefer10.class);

    public static void main(String[] args) {

        Mono.defer(MonoDefer10::createPublisher)
                .subscribe(Util.subscriber());
    }
    public static Mono<Integer> createPublisher(){
        List<Integer> list=List.of(1,2,3,4);;
        return Mono.fromSupplier(()->sum(list));
    }
    private static int sum(List<Integer> list){
        log.info("finding sum of {}",list);
        try {
            Util.sleepSeconds(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list.stream().mapToInt(a->a).sum();
    }
}
