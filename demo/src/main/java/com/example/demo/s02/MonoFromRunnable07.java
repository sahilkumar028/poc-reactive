package com.example.demo.s02;

import com.example.demo.comman.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromRunnable07 {
    private final static Logger log= LoggerFactory.getLogger(MonoFromSuppler05.class);
    public static void main(String[] args) {
        getProductName(10)
                .subscribe(Util.subscriber());
    }
    public static Mono<String> getProductName(int productId){
        if(productId==1){
            return Mono.fromSupplier(()->{
                return switch ((int)(Math.random()*10)){
                    case 0 -> "laptop";
                    case 1 -> "phone";
                    case 2 -> "ac";
                    case 3 -> "washing machine";
                    case 4 -> "freez";
                    case 5 -> "car";
                    case 6 -> "sofa";
                    case 7 -> "toys";
                    case 8 -> "bottel";
                    case 9 -> "remote control car";
                    default -> "jingal bal";
                };
            });
        }
        return Mono.fromRunnable(()->notifyBusiness(productId));
    }

    public static void notifyBusiness(int productId) {
        log.info("notifying business on unavailable {}",productId);
    }
}
