package com.example.demo.s03;

import com.example.demo.assignment.StockObserver;
import com.example.demo.comman.Util;
import com.example.demo.s03.client.ExternalServiceClient;

import java.time.Duration;

public class Assigment12 {
    public static void main(String[] args) throws InterruptedException {
        var client =new ExternalServiceClient();
        var subscriber=new StockObserver();
        client.getPrice()
                .subscribe(subscriber);

        Util.sleepSeconds(20);
    }
}
