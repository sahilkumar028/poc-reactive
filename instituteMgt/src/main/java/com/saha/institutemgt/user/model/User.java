package com.saha.institutemgt.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private boolean emailVerified;

    private String otp;
    private LocalDateTime otpGeneratedAt;
    private Role role;

    private List<String> permissions;

    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
