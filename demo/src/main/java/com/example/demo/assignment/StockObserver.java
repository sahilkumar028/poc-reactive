package com.example.demo.assignment;

import com.example.demo.s01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockObserver implements Subscriber<Integer> {
    private Logger log= LoggerFactory.getLogger(StockObserver.class);
    private int balance=1000;
    private int quantity=0;
    private Subscription subscription;
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        this.subscription=s;
    }

    @Override
    public void onNext(Integer price) {
        if(price<90 && balance>=price){
            log.info("bug the stock at  {}, total quantity:{}, remaing balance:{}",price,quantity,balance);
            quantity++;
            balance=balance-price;
        } else if(price>110 &&  quantity>0){
            log.info("selling {} quantity at {}",price,quantity);
            balance=balance+(quantity*price);
            log.info("total profit:{}",balance-1000);
            quantity=0;
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable t) {
        log.info("error "+t);
    }

    @Override
    public void onComplete() {
        log.info("complete");
    }
}
