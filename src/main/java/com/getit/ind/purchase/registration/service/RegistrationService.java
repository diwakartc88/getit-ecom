package com.getit.ind.purchase.registration.service;

import com.getit.ind.purchase.userProfile.entity.User;
import com.getit.ind.purchase.userProfile.entity.UserProfile;
import com.getit.ind.purchase.registration.request.RegisterRequest;
import com.getit.ind.purchase.userProfile.repository.UserProfileRepository;
import com.getit.ind.purchase.userProfile.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .passwordHash(
                        passwordEncoder.encode(
                                request.getPassword()))
                .status("ACTIVE")
                .emailVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        UserProfile profile = UserProfile.builder()
                .userId(user.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        profileRepository.save(profile);
    }
}
