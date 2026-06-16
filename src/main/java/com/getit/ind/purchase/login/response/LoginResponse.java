package com.getit.ind.purchase.login.response;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private UUID userId;
    private String email;
}
