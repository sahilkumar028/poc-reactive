package com.example.demo.s02;

import com.example.demo.comman.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromCallable06 {
    private static final Logger log= LoggerFactory
            .getLogger(MonoFromSuppler05.class);

    public static void main(String[] args) {

        List<Integer> list=List.of(1,2,3,4);


        Mono.fromCallable(()->sum(list))
                .subscribe(Util.subscriber());
    }
    private static int sum(List<Integer> list) throws Exception{
        log.info("finding sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
