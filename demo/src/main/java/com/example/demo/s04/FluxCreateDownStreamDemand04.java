package com.example.demo.s04;

import com.example.demo.comman.Util;
import com.example.demo.s01.subscriber.SubscriberImpl;
import com.example.demo.s03.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FluxCreateDownStreamDemand04 {
    public static final Logger log= LoggerFactory.getLogger(FluxCreateDownStreamDemand04.class);

    public static void main(String[] args) throws InterruptedException {
        produceOnDemand();
    }

    public static void productEarly() throws InterruptedException {
        var subscriber=new SubscriberImpl();
        Flux.<String>create(fluxSink->{
            for (int i = 0; i <10 ; i++) {
                var name=new ExternalServiceClient().getName().next().block();
                log.info("generated:{}",name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        subscriber.getSubscription().cancel();

    }
    public static void produceOnDemand() throws InterruptedException {
        var subscriber=new SubscriberImpl();
        Flux.<String>create(fluxSink->{
            fluxSink.onRequest(request->{
                for (int i = 0; i <request && !fluxSink.isCancelled() ; i++) {
                    var name=new ExternalServiceClient().getName().next().block();
                    log.info("generated:{}",name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
    }
}
