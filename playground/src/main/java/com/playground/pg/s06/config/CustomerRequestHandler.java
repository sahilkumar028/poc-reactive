package com.playground.pg.s06.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerRequestHandler {
    @Autowired
    
    public Mono<ServerResponse> allCustomers(ServerRequest request){
//        request.pathVariable()
//        request.headers() 

    }
}
