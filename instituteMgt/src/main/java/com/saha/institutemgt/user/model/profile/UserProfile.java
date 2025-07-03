package com.saha.institutemgt.user.model.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("userProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    @Id
    private String id;
    private String userId;
    private String phone;
    private LocalDate dob;
    private Gender gender;
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
