package com.getit.ind.purchase.login.service;

import com.getit.ind.purchase.userProfile.entity.User;
import com.getit.ind.purchase.login.request.LoginRequest;
import com.getit.ind.purchase.login.response.LoginResponse;
import com.getit.ind.purchase.userProfile.repository.UserRepository;
import com.getit.ind.purchase.userProfile.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }
}
