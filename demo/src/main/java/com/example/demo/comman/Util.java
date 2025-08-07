package com.example.demo.comman;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {
    public static <T> Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }
    public static <T> Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<>(name);
    }

    public static void sleepSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }
    public static Faker faker(){
        return Faker.instance();
    }
}
