package com.example.demo.s03;

import com.example.demo.comman.Util;
import com.example.demo.s03.client.ExternalServiceClient;

public class NonBlockingStreamingMessages08 {
    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient client =new ExternalServiceClient();
        client.getName()
//                .log()
                .subscribe(Util.subscriber("sahil"));
        client.getName()
//                .log()
                .subscribe(Util.subscriber("Amit"));

        Util.sleepSeconds(5);
    }
}
