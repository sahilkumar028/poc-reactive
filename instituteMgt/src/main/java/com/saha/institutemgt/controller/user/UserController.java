package com.saha.institutemgt.controller.user;

import com.saha.institutemgt.model.user.Role;
import com.saha.institutemgt.model.user.User;
import com.saha.institutemgt.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;

    @PostMapping(value="/save-user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping(value="/findByEmail/{email}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @GetMapping(value="/getUsers/page",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getActiveUsersPaging(@RequestParam int page,@RequestParam int size){
        return userService.getActiveUsers(page, size);
    }

    @GetMapping(value="/getUsers",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getActiveUsers(){
        return userService.getActiveUsers();
    }

    @PostMapping(value = "/delete",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> deleteUser(String id){
        return userService.deleteuser(id);
    }

    @PutMapping(value="/update",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> updateUserRole(String id, Role newRole){
        return userService.updateUserRole(id, newRole);
    }
}
