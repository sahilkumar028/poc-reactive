package com.springReactiveLearning.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private int id;
    private String fristName;
    private String lastName;
}
public class  ReactiveSources {

    public static Flux<String> stringNumbersFlux(){
        return Flux.just("one","two","three","four","five","six","seven","eight","nine","zero")
                .delayElements(Duration.ofSeconds(1));
    }
    // it will print even numbers
    public static Flux<Integer> intNumberFlux(){
        return Flux.range(0,10)
                .delayElements(Duration.ofMillis(1));
    }
    public static Flux<Integer> intNumberFluxWithException(){
        return Flux.range(0,10)
                .delayElements(Duration.ofSeconds(1))
                .map(e->{
                    if(e==5) throw new RuntimeException("an error happend in the flux");
                    return e;
                });
    }
    public static Mono<Integer> intNumberMono(){
        return Mono.just(42)
                .delayElement(Duration.ofSeconds(1));
    }


    // creating user Flux
    public static Flux<User> userFlux(){
        return Flux.just(
                new User(1,"sahil","kumar"),
                new User(2,"sumit","kumar"),
                new User(3,"alekh","kumar")
        ).delayElements(Duration.ofSeconds(1));
    }


    // creating user Mono
    public static Mono<User> userMono(){
        return Mono.just(new User(1,"sahil","kumar"))
                .delayElement(Duration.ofSeconds(1));
    }


    public static void main(String[] args) throws IOException {
//        ReactiveSources.intNumberFlux()
//                .subscribe(e-> System.out.println(e));

        ReactiveSources.userFlux()
                        .subscribe(e-> System.out.println(e));
        System.out.println("press any key");
        System.in.read();

    }
}
