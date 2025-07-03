package com.saha.institutemgt.user.service.documents;

import com.saha.institutemgt.exception.FileUploadException;
import com.saha.institutemgt.exception.ResourceNotFoundException;
import com.saha.institutemgt.user.model.documents.UserDocuments;
import com.saha.institutemgt.user.repository.documents.UserDocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserDocumentService {

    private final UserDocumentsRepository userDocumentsRepository;
    private static final String BASE_DIR = "institute-data";

    private static final String AADHAAR_FILE = "aadhaar.jpg";
    private static final String MARKSHEET_FILE = "marksheet.jpg";
    private static final String PROFILE_FILE = "profile.jpg";

    public Mono<UserDocuments> uploadUserDocuments(String userId, FilePart aadhaar, FilePart marksheet, FilePart profile) {
        return Mono.when(
                saveFile(userId, AADHAAR_FILE, aadhaar),
                saveFile(userId, MARKSHEET_FILE, marksheet),
                saveFile(userId, PROFILE_FILE, profile)
        ).then(updateDocumentRecord(userId, aadhaar, marksheet, profile));
    }

    public Mono<UserDocuments> updateUserDocuments(String userId, FilePart aadhaar, FilePart marksheet, FilePart profile) {
        return Mono.when(
                saveFile(userId, AADHAAR_FILE, aadhaar),
                saveFile(userId, MARKSHEET_FILE, marksheet),
                saveFile(userId, PROFILE_FILE, profile)
        ).then(userDocumentsRepository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Document record not found for update")))
                .flatMap(doc -> {
                    if (aadhaar != null) doc.setAadhaarUrl("/files/" + userId + "/" + AADHAAR_FILE);
                    if (marksheet != null) doc.setMarksheet10Url("/files/" + userId + "/" + MARKSHEET_FILE);
                    if (profile != null) doc.setProfileImageUrl("/files/" + userId + "/" + PROFILE_FILE);

                    doc.setUploadedAt(LocalDateTime.now());
                    return userDocumentsRepository.save(doc);
                }));
    }

    public Mono<UserDocuments> getUserDocuments(String userId) {
        return userDocumentsRepository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No documents found for this user")));
    }

    public Mono<Void> deleteUserDocuments(String userId) {
        return userDocumentsRepository.findByUserId(userId)
                .flatMap(userDocumentsRepository::delete)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No documents found to delete")));
    }

    private Mono<Void> saveFile(String userId, String fileName, FilePart filePart) {
        if (filePart == null) return Mono.empty();

        return Mono.fromCallable(() -> {
                    Path uploadDir = Paths.get(BASE_DIR, userId);
                    Files.createDirectories(uploadDir);
                    return uploadDir.resolve(fileName);
                })
                .subscribeOn(Schedulers.boundedElastic())  // Non-blocking
                .flatMap(path -> filePart.transferTo(path))
                .onErrorResume(e -> Mono.error(new FileUploadException("File upload failed: " + fileName)));
    }

    private Mono<UserDocuments> updateDocumentRecord(String userId, FilePart aadhaar, FilePart marksheet, FilePart profile) {
        return userDocumentsRepository.findByUserId(userId)
                .defaultIfEmpty(new UserDocuments())
                .flatMap(doc -> {
                    doc.setUserId(userId);
                    if (aadhaar != null) doc.setAadhaarUrl("/files/" + userId + "/" + AADHAAR_FILE);
                    if (marksheet != null) doc.setMarksheet10Url("/files/" + userId + "/" + MARKSHEET_FILE);
                    if (profile != null) doc.setProfileImageUrl("/files/" + userId + "/" + PROFILE_FILE);
                    doc.setUploadedAt(LocalDateTime.now());
                    return userDocumentsRepository.save(doc);
                })
                .onErrorResume(e -> Mono.error(new FileUploadException("Failed to update document record")));
    }
}
