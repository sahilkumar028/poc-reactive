package com.saha.institutemgt.user.repository.documents;

import com.saha.institutemgt.user.model.documents.UserDocuments;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserDocumentsRepository extends ReactiveMongoRepository<UserDocuments,String> {
    Mono<UserDocuments> findByUserId(String userId);
}
