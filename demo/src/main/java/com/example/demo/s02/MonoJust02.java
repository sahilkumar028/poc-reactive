package com.example.demo.s02;

import com.example.demo.s01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class MonoJust02 {
    public static void main(String[] args) throws IOException {
        Mono<String> mono =Mono.just("sahil");
        SubscriberImpl subscriber=new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
    }
}
