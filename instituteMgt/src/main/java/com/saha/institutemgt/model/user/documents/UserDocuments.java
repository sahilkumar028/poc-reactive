package com.saha.institutemgt.model.user.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("userDocuments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDocuments {
    @Id
    private String id;
    private String userId;
    private String aadhaarUrl;
    private String marksheet10Url;
    private String profileImageUrl;

    private List<String> otherDocsUrls;

    private LocalDateTime uploadedAt;
    private LocalDateTime updatedAt;
}
