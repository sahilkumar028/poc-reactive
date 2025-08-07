package com.saha.institutemgt.repository.user.profile;

import com.saha.institutemgt.model.user.profile.UserProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserProfileRepository extends ReactiveMongoRepository<UserProfile,String> {
    Mono<UserProfile> findByUserId(String userId);
    Mono<Boolean> existsByUserId(String userId);
}
