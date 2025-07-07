package com.example.demo.s03.client;

import com.example.demo.comman.AbstrractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstrractHttpClient {
    public Flux<String> getName(){
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getPrice(){
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(i->Integer.parseInt(i));
    }
}
