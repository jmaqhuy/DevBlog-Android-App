package com.example.devblogandroidapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class User {
    private String token;

    @PrimaryKey
    @NonNull
    private String id;
    private String email;
    private String username;
    private String name;
    private String avatarPath;

    private boolean currentUser = false;
}
