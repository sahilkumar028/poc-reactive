package com.springReactiveLearning.demo.entity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class E4 {

    public static void main(String[] args) throws IOException {

//        ReactiveSources.userFlux().subscribe(
//                user-> System.out.println(user),
//                err-> System.err.println(err.getMessage()),
//                ()-> System.out.println("completed user flux")
//        );
//        ReactiveSources.intNumberFlux().subscribe(
//                i-> System.out.println(i),
//                err-> System.err.println(err.getMessage()),
//                ()-> System.out.println("completed int flux")
//        );
//        ReactiveSources.userFlux().subscribe(new MySubscriber<>());
//        ReactiveSources.intNumberFlux().subscribe(new MySubscriber<>());
//        ReactiveSources.intNumberFlux()
//                .filter(i -> i>5)
//                .map(i->i*10)
//                .take(3)
//                .subscribe(System.out::println);
//
//
//        ReactiveSources.intNumberFlux()
//                .filter(i -> i>20)
//                .defaultIfEmpty(-1)
//                .subscribe(System.out::println);
//        ReactiveSources.intNumberFlux()
//                .log()
//                .flatMap(i->ReactiveSources.userFlux().filter(user->user.getId()==i))
//                .subscribe(System.out::println);
//        ReactiveSources.
//

//        ReactiveSources.intNumberFluxWithException()
////                .(err-> System.err.println("error"+ err.getMessage()))
//                .onErrorContinue((e,item)-> System.err.println("error "+e.getMessage()))
//                .subscribe(e-> System.out.println(e),
//                        err-> System.err.println(err.getMessage()));

//        ReactiveSources.intNumberFluxWithException()
//                .onErrorResume(err-> Flux.just(-1,-2))
//                .log()
//                .subscribe(e-> System.out.println(e),
//                        err-> System.err.println(err.getMessage()));

        ReactiveSources.intNumberFlux()
                .delayElements(Duration.ofSeconds(1))
//                .log()
                .subscribe(new MySubscriber());
        System.in.read();
    }
}
class MySubscriber<T> extends BaseSubscriber<T>{
    public void hookOnSubscribe(Subscription s){
        System.out.println("Subscribe happend");
        request(1);
    }

    public void hookOnNext(T v){
        System.out.println(v +" recived");
        request(3);
    }

}
