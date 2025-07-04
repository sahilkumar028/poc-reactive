package com.example.demo.s02;

import reactor.core.publisher.Mono;

public class MonoEmptyError04 {
    public static void main(String[] args) {
        getUserName(11)
                .subscribe(s-> System.out.println(s),
                        err-> System.err.println(err.getMessage()));

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
}
