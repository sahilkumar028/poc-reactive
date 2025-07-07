package com.example.demo.s04;

import com.example.demo.comman.Util;
import com.example.demo.s03.client.ExternalServiceClient;
import com.example.demo.s04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

public class FluxSinkThreadSafety03 {
    private static Logger log= LoggerFactory.getLogger(FluxSinkThreadSafety03.class);

    public static void main(String[] args) throws InterruptedException {
        demo2();
    }
    private static void demo1() throws InterruptedException {
        List<Integer> list =new ArrayList<>();
        Runnable runnable=()->{
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        };
        for(int i=0;i<10;i++){
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        System.out.println(list.size());
    }
    private static void demo2() throws InterruptedException {
        List<String> list =new ArrayList<>();
        var generator=new NameGenerator();
        Flux<String> flux= Flux.create(generator);
        flux.subscribe(name->list.add(name));
        Runnable runnable=()->{
            for(int i=0;i<1000;i++){
                generator.generate();
            }
        };
        for(int i=0;i<20;i++){
            new Thread(runnable).start();
        }
        Util.sleepSeconds(10);
        System.out.println(list.size());
    }
}
