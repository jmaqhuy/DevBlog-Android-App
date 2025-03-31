package com.example.devblogandroidapp.api.response;

import com.example.devblogandroidapp.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse extends User {
    public RegisterResponse(String token, String id, String email, String username, String name, String avatarPath, boolean currentUser) {
        super(token, id, email, username, name, avatarPath, currentUser);
    }
}
