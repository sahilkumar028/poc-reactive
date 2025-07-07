package com.example.demo.s02;

import com.example.demo.comman.Util;
import com.example.demo.s02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonblockingIO11 {
    public static final Logger log= LoggerFactory.getLogger(NonblockingIO11.class);

    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient client =new ExternalServiceClient();
        //non bloacking
        System.out.println("non blocking calls");
        for(int i=1;i<=100;i++){
            client.getProductName(i)
                .subscribe(e->System.out.println("recivers :"+e));
        }
        Util.sleepSeconds(5);
        //blocking
        System.out.println("blocking calls");
        for(int i=1;i<=100;i++){
            String s=client.getProductName(i).block();
            System.out.println("block state data"+s);
//                .subscribe(e->System.out.println("recivers :"+e));
        }
    }
}
