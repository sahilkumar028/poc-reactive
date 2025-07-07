package com.example.demo.s03;

import com.example.demo.comman.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FLuxMono11 {
    public static void main(String[] args) {
//        Mono<String> mono =getUserName(4);
//        save(Flux.from(mono));
        var flux=Flux.range(1,10);
        Mono.from(flux)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getUserName(int userId){
        return switch (userId){
            case 1-> Mono.just("sahil");
            case 2-> Mono.just("Golu");
            case 3-> Mono.just("sumit");
            case 4-> Mono.just("alekh");
            case 5-> Mono.just("harsh");
            default-> Mono.error(new RuntimeException("invalid input"));
        };
    }
    public static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
