package com.example.demo.comman;

import com.example.demo.s01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber <T>implements Subscriber<T> {

    private String name;
    private static final Logger log= LoggerFactory.getLogger(SubscriberImpl.class);
    public DefaultSubscriber(String name){
        this.name=name;
    }
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        log.info("{} recived:{}",this.name,t);
    }

    @Override
    public void onError(Throwable t) {
        log.error("{} error",this.name,t);
    }

    @Override
    public void onComplete() {
        log.info("{} complete!",this.name);
    }
}
