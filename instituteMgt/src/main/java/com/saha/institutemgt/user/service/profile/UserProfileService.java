package com.saha.institutemgt.user.service.profile;

import com.saha.institutemgt.exception.ResourceAlreadyExistsException;
import com.saha.institutemgt.exception.ResourceNotFoundException;
import com.saha.institutemgt.user.model.profile.Gender;
import com.saha.institutemgt.user.model.profile.UserProfile;
import com.saha.institutemgt.user.repository.profile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public Mono<UserProfile> createUserProfile(UserProfile userProfile){
        return userProfileRepository.existsByUserId(userProfile.getUserId())
                .flatMap(e->{
                    if(e) return Mono.error(new ResourceAlreadyExistsException("userid profile already exist"));
                    userProfile.setCreatedAt(LocalDateTime.now());
                    userProfile.setUpdatedAt(LocalDateTime.now());
                    return userProfileRepository.save(userProfile);
                });
    }

    public Mono<UserProfile> getUserProfile(String userId){
        return userProfileRepository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("does not exit")));
    }

    public Mono<UserProfile> updateUserProfile(String userId,UserProfile userProfile){
        return getUserProfile(userId)
                .flatMap(e->{
                    e.setPhone(userProfile.getPhone());
                    e.setDob(userProfile.getDob());
                    e.setAddress(userProfile.getAddress());
                    e.setGender(userProfile.getGender());
                    e.setUpdatedAt(LocalDateTime.now());
                    return userProfileRepository.save(e);
                });
    }

}
