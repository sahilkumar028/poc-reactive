package com.example.demo.s02.client;

import com.example.demo.comman.AbstrractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstrractHttpClient {
    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }
}
