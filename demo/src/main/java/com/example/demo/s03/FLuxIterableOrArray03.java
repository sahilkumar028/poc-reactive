package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FLuxIterableOrArray03 {
    public static void main(String[] args) {
        List<String> list=List.of("a","b","c","d");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());

        Integer a[]={10,20,30,40,50};
        Flux.fromArray(a)
//                .map(i->i/10)
                .subscribe(Util.subscriber());

    }
}
