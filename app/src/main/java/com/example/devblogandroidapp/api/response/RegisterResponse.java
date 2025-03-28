package com.example.devblogandroidapp.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private String token;
    private String id;
    private String email;
    private String username;
    private String name;
    private String avatarPath;
}
