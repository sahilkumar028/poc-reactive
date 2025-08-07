package com.whatsapp.api.service;


import com.whatsapp.api.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WhatsappService {
    public Mono<String> sendMessage(Message m){
        log.info("message send:{}",m);
        return Mono.just("Message send to "+ m.getReceiver());
    }

    public Mono<String> receiveMessage(Message m){
        log.info("message receive:{}",m.getSender());
        return Mono.just("Received Message from "+ m.getSender());
    }
}
