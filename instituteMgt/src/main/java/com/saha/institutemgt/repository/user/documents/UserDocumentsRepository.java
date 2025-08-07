package com.saha.institutemgt.repository.user.documents;

import com.saha.institutemgt.model.user.documents.UserDocuments;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserDocumentsRepository extends ReactiveMongoRepository<UserDocuments,String> {
    Mono<UserDocuments> findByUserId(String userId);
}
