package com.example.demo.s04;

import com.example.demo.comman.Util;
import com.example.demo.s04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class FluxCreateRefactor02 {
    public static void main(String[] args) {
        var generator =new NameGenerator();
        var flux= Flux.create(generator);
        flux.subscribe(Util.subscriber());
        for(int i=0;i<10;i++){
                generator.generate();
        }
    }
}
