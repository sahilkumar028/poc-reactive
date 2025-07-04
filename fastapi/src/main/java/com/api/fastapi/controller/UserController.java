package com.api.fastapi.controller;

import com.api.fastapi.model.User;
import com.api.fastapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;

    @GetMapping(value = "/getName", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAllName(){
        return userService.getAllUserName();
    }

    @GetMapping(value="/allUsers",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUsers(){
        return userService.getAllUser();
    }
    @PostMapping("/saveUser")
    public Mono<User> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("/getByName/{name}")
    public Flux<User> getUserByName(@RequestParam String name){
        return userService.getByName(name);
    }
    @GetMapping("/getById/{id}")
    public Mono<User> getUserById(@RequestParam String id){
        return userService.getById(id);
    }
    @GetMapping(value = "/fastRandom",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFastRandomPassword(){
        return userService.getMultipleUniquePassword();
    }

}
