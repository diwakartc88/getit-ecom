package com.getit.ind.purchase.userProfile.service;


import com.getit.ind.purchase.userProfile.entity.User;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class JwtService {
    private final String secret = "my-super-secret-key-my-super-secret-key";

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 900000)) // 15 min
                .signWith(
                        Keys.hmacShaKeyFor(secret.getBytes()),
                        Jwts.SIG.HS256
                )
                .compact();
    }
}
