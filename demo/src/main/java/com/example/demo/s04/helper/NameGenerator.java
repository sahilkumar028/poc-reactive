package com.example.demo.s04.helper;

import com.example.demo.s03.client.ExternalServiceClient;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink=stringFluxSink;
    }
    public void generate(){
        ExternalServiceClient externalServiceClient=new ExternalServiceClient();
        String name= externalServiceClient.getName().next().block();
        this.fluxSink.next(name != null ? name : "");
    }

}
