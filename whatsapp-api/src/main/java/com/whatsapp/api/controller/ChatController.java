package com.whatsapp.api.controller;


import com.whatsapp.api.model.Message;
import com.whatsapp.api.service.FirebaseService;
import com.whatsapp.api.service.WhatsappService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final WhatsappService whatsappService;
    private final FirebaseService firebaseService;
    @PostMapping("/send")
    public Mono<String> sendMessage(@RequestBody Message m){
        if(m.getTimestamp()==null){
            m.setTimestamp(LocalDateTime.now());
        }
        return whatsappService.sendMessage(m).flatMap(r -> firebaseService.saveMessage(m));
    }

    @PostMapping("/receive")
    public Mono<String> recevieMessage(@RequestBody Message m){
        if(m.getTimestamp()==null){
            m.setTimestamp(LocalDateTime.now());
        }
        return whatsappService.receiveMessage(m)
                .flatMap(res -> firebaseService.saveMessage(m));
    }

//    @GetMapping("/history")
//    public Mono<List<Message>> getHistory() {
//        return firebaseService.getAllMessage();
//    }
}
