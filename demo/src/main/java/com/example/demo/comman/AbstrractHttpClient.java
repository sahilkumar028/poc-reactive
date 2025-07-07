package com.example.demo.comman;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public class AbstrractHttpClient {
    private static final String URL="http://localhost:7070";
    protected  final HttpClient httpClient;

    public AbstrractHttpClient(){
        LoopResources loopResources=LoopResources.create("vins",1,true);
        this.httpClient=HttpClient.create().runOn(loopResources).baseUrl(URL);
    }
}
