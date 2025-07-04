package com.api.fastapi.service;

import com.api.fastapi.model.User;
import com.api.fastapi.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {
    final private UserRepo userRepo;

    public Flux<String> getAllUserName(){
        return userRepo.findAll()
                .delayElements(Duration.ofMillis(1))
                .map(User::getName);
    }

    public Mono<User> saveUser(User user){
        return userRepo.save(user);
    }

    public Mono<User> getById(String id){
        return userRepo.findById(id);
    }

    public Flux<User> getByName(String name){
        return userRepo.findByName(name)
                .delayElements(Duration.ofSeconds(1));
    }
    public Flux<String> getMultipleUniquePassword(){
        return Flux.range(1,10000)
                .map(i->((char)(Math.random()*30+65))+"@#"+(int)(Math.random()*1000))
                .delayElements(Duration.ofMillis(1));
    }

    public Flux<User> getAllUser(){
        return userRepo.findAll()
                .delayElements(Duration.ofMillis(5000));
    }

}
