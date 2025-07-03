package com.api.fastapi.repo;

import com.api.fastapi.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveMongoRepository<User,String> {
    Flux<User> findByName(String name);
}
