package com.whatsapp.api.service;

import com.whatsapp.api.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FirebaseService {
    private static final String COLLECTION_NAME="message";

    public Mono <String> saveMessage(Message m){
    return Mono.empty();
    }
}
