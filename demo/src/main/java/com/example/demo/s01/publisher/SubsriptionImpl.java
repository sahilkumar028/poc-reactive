package com.example.demo.s01.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubsriptionImpl implements Subscription {
    private final static Logger log= LoggerFactory.getLogger(SubsriptionImpl.class);
    private final Subscriber<? super String> s;
    private boolean isCancel;
    private static final int Max=10;
    private int count=0;
    public SubsriptionImpl(Subscriber<? super String> s){
        this.s=s;
    }
    @Override
    public void request(long n) {
        if(isCancel){
            return;
        }
        log.info("subsriber has requested {} itens",n);
        if(n>Max){
            this.s.onError(new RuntimeException("validate failed"));
            this.isCancel=true;
            return;
        }
        for(int i=0;i<n && count<Max;i++){
            count++;
            this.s.onNext((char)((int)(Math.random()*20+65))+"@gmail.com");
        }
        if(count==Max){
            log.info("no more data to produce");
            this.s.onComplete();
            this.isCancel=true;
        }
    }

    @Override
    public void cancel() {
        log.info("subsriber has cancelled");
        isCancel=true;
    }
}
