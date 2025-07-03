package com.saha.institutemgt.user.service;


import com.saha.institutemgt.exception.ResourceAlreadyExistsException;
import com.saha.institutemgt.exception.ResourceNotFoundException;
import com.saha.institutemgt.user.model.Role;
import com.saha.institutemgt.user.model.User;
import com.saha.institutemgt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Mono<User> createUser(User user){
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(e->{
                   if(e){
                       return Mono.error(new ResourceAlreadyExistsException("Email already registered"));
                   }
                   applyDefault(user);
                   return userRepository.save(user);
                });
    }

    private void applyDefault(User user){
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setEmailVerified(false);
        user.setActive(true);
    }
    public Flux<User> getActiveUsers(int page, int size) {
        int skip = page * size;
        return userRepository.findByActiveTrue()
                .skip(skip)
                .take(size)
                .delayElements(Duration.ofSeconds(1)); // Optional: stream slow
    }

    public Flux<User> getActiveUsers() {
        return userRepository.findByActiveTrue();
    }

    public Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found")));
    }

    public Mono<User> deleteuser(String id){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found")))
                .flatMap(user->{
                    user.setActive(false);
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                });
    }
    public Mono<User> updateUserRole(String id, Role newRole){
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("user not found")))
                .flatMap(user->{
                    user.setRole(newRole);
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                });
    }

    
}
