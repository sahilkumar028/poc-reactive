package com.example.demo.s02;

import com.example.demo.s01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class MonoJust02 {
    public static void main(String[] args) throws IOException {
        SubscriberImpl subscriber=new SubscriberImpl();
        Mono<String> mono =Mono.just("sahil");
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
    }
}
