package com.saha.institutemgt.user.repository;

import com.saha.institutemgt.user.model.Role;
import com.saha.institutemgt.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Mono<User> findByEmail(String email);
    Mono<Boolean> existsByEmail(String email);
    Flux<User> findByActiveTrue(Pageable pageable);
    Flux<User> findByActiveTrue();

}
