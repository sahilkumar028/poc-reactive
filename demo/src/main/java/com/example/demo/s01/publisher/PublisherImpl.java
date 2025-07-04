package com.example.demo.s01.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> s) {
        var subscription =new SubsriptionImpl(s);
        s.onSubscribe(subscription);
    }
}
