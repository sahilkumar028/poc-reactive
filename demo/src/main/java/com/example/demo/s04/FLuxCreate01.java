package com.example.demo.s04;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;

public class FLuxCreate01 {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    for(int i=65;i<=90;i++){
                        fluxSink.next((char)i);
                        if(i==70){
                            break;
                        }
                    }
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber());
    }
}
